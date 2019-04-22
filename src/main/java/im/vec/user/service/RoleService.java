package im.vec.user.service;

import cc.eamon.open.status.StatusException;
import im.vec.user.dao.*;
import im.vec.user.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static im.vec.user.service.ExampleUtils.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 */
@Service
public class RoleService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermitDao permitDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RolePermitDao rolePermitDao;

    /**
     * 分页计数函数
     */
    private RoleExample initPageRowQueryFilter(Long page, Integer rows) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);
        if (page > 0) {
            Long offset = (page - 1L) * rows;
            example.setLimit(rows);
            example.setOffset(offset);
        }
        return example;
    }

    private int updateUsers(List<Integer> userIds, String createBy, String updateBy, Role entity) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        if (userIds != null && userIds.size() != 0) {
            // 查询并插入用户信息
            List<User> users = userDao.selectByExample(initUserIdsQueryFilter(userIds));
            if (users.size() != userIds.size()) throw new StatusException("USER_NULL");
            //删除已经使用此角色的关系
            count.getAndAdd(userRoleDao.updateByExampleSelective(new UserRole((byte) 1, updateBy), initUserRoleByRoleIdQueryFilter(entity.getId())));
            // 角色信息插入
            users.forEach(user -> {
                // 插入新的角色关系
                // 第一步 删除原来的用户角色关系
                // 第二步 插入新关系 TODO: ATTENTION 主键重复替换
                count.getAndAdd(userRoleDao.updateByExampleSelective(new UserRole((byte) 1, updateBy), initUserRoleByUserIdQueryFilter(user.getId())));
                count.getAndAdd(userRoleDao.insertSelective(new UserRole(user.getId(), entity.getId(), createBy, updateBy)));
            });
        }
        return count.get();
    }

    private int updatePermits(List<String> permitStrings, String createBy, String updateBy, Role entity) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        if (permitStrings != null && permitStrings.size() != 0) {
            // 查询并插入权限信息
            Map<Integer, String> permitMap = new HashMap<>();
            for (String s : permitStrings) {
                String data[] = s.split(":");
                if (data.length < 2) throw new StatusException("WRONG_DATA_FORMAT");
                Integer key = Integer.parseInt(data[0]);
                permitMap.computeIfPresent(key, (k, v) -> v + "," + data[1]);
                permitMap.computeIfAbsent(key, k -> data[1]);
            }
            Set<Integer> permitIds = permitMap.keySet();

            List<Permit> permits = permitDao.selectByExample(initPermitIdsQueryFilter(new ArrayList<>(permitIds)));
            if (permits.size() != permitMap.size()) throw new StatusException("PERMIT_NULL");
            // 第一步 删除原来的关系
            count.getAndAdd(rolePermitDao.updateByExampleSelective(new RolePermit((byte) 1, updateBy), initRolePermitByRoleIdQueryFilter(entity.getId())));

            permits.forEach(permit -> {
                        // 第二步 插入新关系 TODO: ATTENTION 主键重复替换
                        count.getAndAdd(rolePermitDao.insertSelective(
                                new RolePermit(
                                        entity.getId(),
                                        permit.getId(),
                                        permitMap.get(permit.getId()),
                                        createBy,
                                        updateBy
                                )
                        ));
                    }
            );
        }
        return count.get();
    }


    public Long getRoleCount() throws StatusException {
        return roleDao.countByExample(initPageRowQueryFilter(0L, 0));
    }

    public Role getRoleById(Integer id) throws StatusException {
        return roleDao.selectByPrimaryKey(id);
    }

    public Map<String, Object> getRoleSimpleMapById(Integer id) throws StatusException {
        Role entity = roleDao.selectByPrimaryKey(id);
        if (entity == null) throw new StatusException("ROLE_NULL");
        return RoleSimpleMapper.getMapWithExtra(entity, userRoleDao.countByExample(initUserRoleByRoleIdQueryFilter(entity.getId())));
    }

    public Map<String, Object> getRoleDetailMapById(Integer id) throws StatusException {
        Role entity = roleDao.selectByPrimaryKey(id);
        if (entity == null) throw new StatusException("ROLE_NULL");
        List<Map<String, Object>> userList = new ArrayList<>();
        userDao.selectUsersByRoleId(entity.getId()).forEach(
                user -> userList.add(UserDetailMapper.getMap(user))
        );

        List<Map<String, Object>> permitList = new ArrayList<>();
        permitDao.selectPermitsByRoleIdWithExtra(entity.getId()).forEach(
                mapper ->
                        permitList.add(
                                PermitDetailMapper.getMapWithExtra(
                                        mapper.getEntity(),
                                        mapper.operation
                                )
                        )
        );

        return RoleDetailMapper.getMapWithExtra(entity, userList, permitList, (long) userList.size());
    }

    public List<Map<String, Object>> getRoleSimpleMapList(Long page, Integer rows) throws StatusException {
        List<Map<String, Object>> entityMapList = new ArrayList<>();
        roleDao.selectByExample(initPageRowQueryFilter(page, rows)).forEach(
                entityMap ->
                        entityMapList.add(
                                RoleSimpleMapper.getMapWithExtra(
                                        entityMap,
                                        userRoleDao.countByExample(initUserRoleByRoleIdQueryFilter(entityMap.getId()))
                                )
                        )
        );
        return entityMapList;
    }

    public List<Map<String, Object>> getRoleDetailMapList(Long page, Integer rows) throws StatusException {
        List<Map<String, Object>> entityMapList = new ArrayList<>();
        roleDao.selectByExample(initPageRowQueryFilter(page, rows)).forEach(
                entityMap ->
                        entityMapList.add(
                                RoleSimpleMapper.getMapWithExtra(
                                        entityMap,
                                        userRoleDao.countByExample(initUserRoleByRoleIdQueryFilter(entityMap.getId()))
                                )
                        )
        );
        return entityMapList;
    }

    public Integer postRole(RolePostMapper postMapper, String createBy, String updateBy) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        // 从上传数据中拿出记录;
        Role entity = postMapper.getEntity();
        // 记录创建者;
        entity.setCreateBy(createBy);
        // 记录更新者;
        entity.setUpdateBy(updateBy);

        try {
            // 持久化对象，Id将自动产生;
            count.addAndGet(roleDao.insertSelective(entity));
        } catch (Exception e) {
            //TODO: 若改角色isDelete状态为1 需要进行彻底删除或备份删除操作
            throw new StatusException("ROLE_EXIST");
        }

        count.getAndAdd(updateUsers(postMapper.users, createBy, updateBy, entity));
        count.getAndAdd(updatePermits(postMapper.permits, createBy, updateBy, entity));

        return count.get();
    }


    public Integer postRoleList(List<RolePostMapper> postMappers, String createBy, String updateBy) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity均上传;
        for (RolePostMapper postMapper : postMappers) {
            // 插入计数;
            count.addAndGet(postRole(postMapper, createBy, updateBy));
        }
        return count.get();
    }

    public Integer updateRole(RoleUpdateMapper updateMapper, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        Role entity = updateMapper.getEntity();
        // 记录更新者;
        entity.setUpdateBy(updateBy);
        count.addAndGet(roleDao.updateByPrimaryKeySelective(entity));
        if (count.get() == 0) throw new StatusException("ROLE_NULL");

        count.getAndAdd(updateUsers(updateMapper.users, updateBy, updateBy, entity));
        count.getAndAdd(updatePermits(updateMapper.permits, updateBy, updateBy, entity));

        return count.get();
    }

    public Integer updateRoleList(List<RoleUpdateMapper> updateMappers, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (RoleUpdateMapper updateMapper : updateMappers) {
            // 更新计数;
            count.addAndGet(updateRole(updateMapper, updateBy));
        }
        return count.get();
    }

    public Integer deleteRole(Integer id, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        Role entity = new Role();
        // 记录更新者;
        entity.setId(id);
        entity.setUpdateBy(updateBy);
        // 删除条目;
        entity.setIsDelete((byte) 1);
        count.addAndGet(roleDao.updateByPrimaryKeySelective(entity));
        return count.get();
    }

    public Integer deleteRoleList(List<Integer> ids, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (Integer id : ids) {
            // 更新计数;
            count.addAndGet(deleteRole(id, updateBy));
        }
        return count.get();
    }

    public Integer recoverRole(Integer id, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        Role entity = new Role();
        // 记录更新者;
        entity.setId(id);
        entity.setUpdateBy(updateBy);
        // 恢复条目;
        entity.setIsDelete((byte) 0);
        count.addAndGet(roleDao.updateByPrimaryKeySelective(entity));
        return count.get();
    }

    public Integer recoverRoleList(List<Integer> ids, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (Integer id : ids) {
            // 更新计数;
            count.addAndGet(recoverRole(id, updateBy));
        }
        return count.get();
    }
}
