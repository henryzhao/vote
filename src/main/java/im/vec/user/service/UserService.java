package im.vec.user.service;

import cc.eamon.open.status.StatusException;
import im.vec.user.dao.PermitDao;
import im.vec.user.dao.RoleDao;
import im.vec.user.dao.UserDao;
import im.vec.user.dao.UserRoleDao;
import im.vec.user.entity.*;
import im.vec.util.PwdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static im.vec.user.service.ExampleUtils.initRoleIdsQueryFilter;
import static im.vec.user.service.ExampleUtils.initUserRoleByUserIdQueryFilter;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermitDao permitDao;

    /**
     * 分页计数函数
     */
    private UserExample initPageRowQueryFilter(Long page, Integer rows) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);
        if (page > 0) {
            Long offset = (page - 1L) * rows;
            example.setLimit(rows);
            example.setOffset(offset);
        }
        return example;
    }


    public Long getUserCount() throws StatusException {
        return userDao.countByExample(initPageRowQueryFilter(0L, 0));
    }

    public User getUserById(Integer id) throws StatusException {
        return userDao.selectByPrimaryKey(id);
    }

    public Map<String, Object> getUserSimpleMapById(Integer id) throws StatusException {
        User entity = userDao.selectByPrimaryKey(id);
        if (entity == null) throw new StatusException("USER_NULL");
        return UserSimpleMapper.getMap(entity);
    }

    public Map<String, Object> getUserDetailMapById(Integer id) throws StatusException {
        User entity = userDao.selectByPrimaryKey(id);
        if (entity == null) throw new StatusException("USER_NULL");
        List<Map<String, Object>> roleMap = new ArrayList<>();
        AtomicReference<Role> role = new AtomicReference<>();
        roleDao.selectRolesByUserId(entity.getId()).forEach(r -> {
            roleMap.add(RoleDetailMapper.getMap(r));
            role.set(r);
        });
        List<Map<String, Object>> permitMap = new ArrayList<>();
        if (role.get() != null){
            permitDao.selectPermitsByRoleIdWithExtra(role.get().getId()).forEach(p -> {
                permitMap.add(PermitDetailMapper.getMapWithExtra(p.getEntity(), p.operation));
            });
        }
        return UserDetailMapper.getMapWithExtra(entity, roleMap, permitMap);
    }

    public List<Map<String, Object>> getUserSimpleMapList(Long page, Integer rows) throws StatusException {
        List<Map<String, Object>> entityMapList = new ArrayList<>();
        userDao.selectByExample(initPageRowQueryFilter(page, rows)).forEach(
                entityMap -> entityMapList.add(UserSimpleMapper.getMap(entityMap))
        );
        return entityMapList;
    }

    public List<Map<String, Object>> getUserDetailMapList(Long page, Integer rows) throws StatusException {
        List<Map<String, Object>> entityMapList = new ArrayList<>();
        userDao.selectByExample(initPageRowQueryFilter(page, rows)).forEach(
                entityMap -> {
                    List<Map<String, Object>> roleMap = new ArrayList<>();
                    AtomicReference<Role> role = new AtomicReference<>();
                    roleDao.selectRolesByUserId(entityMap.getId()).forEach(r -> {
                        roleMap.add(RoleDetailMapper.getMap(r));
                        role.set(r);
                    });
                    List<Map<String, Object>> permitMap = new ArrayList<>();
                    if (role.get() != null){
                        permitDao.selectPermitsByRoleId(role.get().getId()).forEach(p -> {
                            permitMap.add(PermitDetailMapper.getMap(p));
                        });
                    }
                    entityMapList.add(UserDetailMapper.getMapWithExtra(entityMap, roleMap, permitMap));
                }
        );
        return entityMapList;
    }

    public Integer postUser(UserPostMapper postMapper, String createBy, String updateBy) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        // 从上传数据中拿出记录;
        User entity = postMapper.getEntity();
        entity.setSalt(PwdUtils.salt());
        entity.setPassword(PwdUtils.saltPassword(entity.getPassword(), entity.getSalt()));
        // 记录创建者;
        entity.setCreateBy(createBy);
        // 记录更新者;
        entity.setUpdateBy(updateBy);
        try {
            // 插入用户信息
            count.addAndGet(userDao.insertSelective(entity));
        }catch (Exception e){
            //TODO: 若改账户isDelete状态为1 需要进行彻底删除或备份删除操作
            throw new StatusException("USER_EXIST");
        }
        // 查询角色信息
        if (postMapper.roles != null && postMapper.roles.size() != 0) {
            List<Role> roles = roleDao.selectByExample(initRoleIdsQueryFilter(postMapper.roles));
            if (roles.size() != postMapper.roles.size()) throw new StatusException("ROLE_NULL");
            if (postMapper.roles.size() != 1) throw new StatusException("ROLE_ERROR");
            // 插入角色信息
            roles.forEach((role ->
                    count.getAndAdd(userRoleDao.insertSelective(new UserRole(entity.getId(), role.getId(), createBy, updateBy)))
            ));
        }

        return count.get();
    }

    public Integer postUserList(List<UserPostMapper> postMappers, String createBy, String updateBy) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity均上传;
        for (UserPostMapper postMapper : postMappers) {
            // 插入计数;
            count.addAndGet(postUser(postMapper, createBy, updateBy));
        }
        return count.get();
    }

    public Integer updateUser(UserUpdateMapper updateMapper, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        User entity = updateMapper.getEntity();
        if (entity.getPassword() != null) {
            entity.setSalt(PwdUtils.salt());
            entity.setPassword(PwdUtils.saltPassword(entity.getPassword(), entity.getSalt()));
        }
        // 记录更新者;
        entity.setUpdateBy(updateBy);
        count.addAndGet(userDao.updateByPrimaryKeySelective(entity));
        if (count.get() == 0) throw new StatusException("USER_NULL");
        // 查询并插入角色信息
        if (updateMapper.roles != null && updateMapper.roles.size() != 0) {
            List<Role> roles = roleDao.selectByExample(initRoleIdsQueryFilter(updateMapper.roles));
            if (roles.size() != updateMapper.roles.size()) throw new StatusException("ROLE_NULL");
            if (updateMapper.roles.size() != 1) throw new StatusException("ROLE_ERROR");
            // 更新角色信息
            // 第一步 删除原来的关系
            count.getAndAdd(userRoleDao.updateByExampleSelective(new UserRole((byte) 1, updateBy), initUserRoleByUserIdQueryFilter(entity.getId())));
            // 第二步 插入新关系 TODO: ATTENTION 主键重复替换
            roles.forEach(role ->
                    count.getAndAdd(userRoleDao.insertSelective(new UserRole(entity.getId(), role.getId(), updateBy, updateBy)))
            );
        }
        return count.get();
    }

    public Integer updateUserList(List<UserUpdateMapper> updateMappers, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (UserUpdateMapper updateMapper : updateMappers) {
            // 更新计数;
            count.addAndGet(updateUser(updateMapper, updateBy));
        }
        return count.get();
    }

    public Integer deleteUser(Integer id, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        User entity = new User();
        // 记录更新者;
        entity.setId(id);
        entity.setUpdateBy(updateBy);
        // 删除User条目;
        entity.setIsDelete((byte) 1);
        count.addAndGet(userDao.updateByPrimaryKeySelective(entity));
        // 删除关系条目
        count.getAndAdd(userRoleDao.updateByExampleSelective(new UserRole((byte) 1, updateBy), initUserRoleByUserIdQueryFilter(entity.getId())));
        return count.get();
    }

    public Integer deleteUserList(List<Integer> ids, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (Integer id : ids) {
            // 更新计数;
            count.addAndGet(deleteUser(id, updateBy));
        }
        return count.get();
    }

    public Integer recoverUser(Integer id, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        User entity = new User();
        // 记录更新者;
        entity.setId(id);
        entity.setUpdateBy(updateBy);
        // 恢复条目;
        entity.setIsDelete((byte) 0);
        count.addAndGet(userDao.updateByPrimaryKeySelective(entity));
        // 恢复关系条目;
        count.getAndAdd(userRoleDao.recoverByUserId(entity.getId()));
        return count.get();
    }

    public Integer recoverUserList(List<Integer> ids, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (Integer id : ids) {
            // 更新计数;
            count.addAndGet(recoverUser(id, updateBy));
        }
        return count.get();
    }

    /**
     * ==================================
     * 非CRUD业务逻辑代码
     * ==================================
     */

    public Map<String, Object> login(String account, String password) throws StatusException {
        User user = userDao.selectByAccount(account);
        if (user == null) throw new StatusException("USER_NULL");
        if (!PwdUtils.checkPassword(user.getPassword(), user.getSalt(), password))
            throw new StatusException("PASSWORD_ERROR");
        user.setToken(PwdUtils.token());
        userDao.updateByPrimaryKeySelective(user);
        return getUserDetailMapById(user.getId());
    }

    public int logout(int userId) throws StatusException {
        User user = getUserById(userId);
        if (user == null) throw new StatusException("USER_NULL");
        user.setToken("");
        return userDao.updateByPrimaryKeySelective(user);
    }


}
