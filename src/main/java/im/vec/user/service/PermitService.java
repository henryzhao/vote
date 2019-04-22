package im.vec.user.service;

import cc.eamon.open.status.StatusException;
import im.vec.user.dao.PermitDao;
import im.vec.user.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 */
@Service
public class PermitService {
    @Autowired
    private PermitDao permitDao;

    /**
     * 分页计数函数
     */
    private PermitExample initPageRowQueryFilter(Long page, Integer rows) {
        PermitExample example = new PermitExample();
        PermitExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);
        if (page > 0) {
            Long offset = (page - 1L) * rows;
            example.setLimit(rows);
            example.setOffset(offset);
        }
        return example;
    }

    public Long getPermitCount() throws StatusException {
        return permitDao.countByExample(initPageRowQueryFilter(0L, 0));
    }

    public Permit getPermitById(Integer id) throws StatusException {
        return permitDao.selectByPrimaryKey(id);
    }

    public Map<String, Object> getPermitSimpleMapById(Integer id) throws StatusException {
        Permit entity = permitDao.selectByPrimaryKey(id);
        if (entity == null) throw new StatusException("PERMIT_NULL");
        return PermitSimpleMapper.getMap(entity);
    }

    public Map<String, Object> getPermitDetailMapById(Integer id) throws StatusException {
        Permit entity = permitDao.selectByPrimaryKey(id);
        if (entity == null) throw new StatusException("PERMIT_NULL");
        return PermitDetailMapper.getMap(entity);
    }

    public List<Map<String, Object>> getPermitSimpleMapList(Long page, Integer rows) throws StatusException {
        List<Map<String, Object>> entityMapList = new ArrayList<>();
        permitDao.selectByExample(initPageRowQueryFilter(page, rows)).forEach(
                entityMap -> entityMapList.add(PermitSimpleMapper.getMap(entityMap))
        );
        return entityMapList;
    }

    public List<Map<String, Object>> getPermitDetailMapList(Long page, Integer rows) throws StatusException {
        List<Map<String, Object>> entityMapList = new ArrayList<>();
        permitDao.selectByExample(initPageRowQueryFilter(page, rows)).forEach(
                entityMap -> entityMapList.add(PermitDetailMapper.getMap(entityMap))
        );
        return entityMapList;
    }

    public Integer postPermit(PermitPostMapper postMapper, String createBy, String updateBy) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        // 从上传数据中拿出记录;
        Permit entity = postMapper.getEntity();
        // 记录创建者;
        entity.setCreateBy(createBy);
        // 记录更新者;
        entity.setUpdateBy(updateBy);
        // 持久化对象，Id将自动产生;
        count.addAndGet(permitDao.insertSelective(entity));
        return count.get();
    }

    public Integer postPermitList(List<PermitPostMapper> postMappers, String createBy, String updateBy) throws StatusException {
        // 记录上传条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity均上传;
        for (PermitPostMapper postMapper : postMappers) {
            // 插入计数;
            count.addAndGet(postPermit(postMapper, createBy, updateBy));
        }
        return count.get();
    }

    public Integer updatePermit(PermitUpdateMapper updateMapper, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        Permit entity = updateMapper.getEntity();
        // 记录更新者;
        entity.setUpdateBy(updateBy);
        count.addAndGet(permitDao.updateByPrimaryKeySelective(entity));
        return count.get();
    }

    public Integer updatePermitList(List<PermitUpdateMapper> updateMappers, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (PermitUpdateMapper updateMapper : updateMappers) {
            // 更新计数;
            count.addAndGet(updatePermit(updateMapper, updateBy));
        }
        return count.get();
    }

    public Integer deletePermit(Integer id, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        Permit entity = new Permit();
        // 记录更新者;
        entity.setId(id);
        entity.setUpdateBy(updateBy);
        // 删除条目;
        entity.setIsDelete((byte) 1);
        count.addAndGet(permitDao.updateByPrimaryKeySelective(entity));
        return count.get();
    }

    public Integer deletePermitList(List<Integer> ids, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (Integer id : ids) {
            // 更新计数;
            count.addAndGet(deletePermit(id, updateBy));
        }
        return count.get();
    }

    public Integer recoverPermit(Integer id, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 从更新数据中拿出记录;
        Permit entity = new Permit();
        // 记录更新者;
        entity.setId(id);
        entity.setUpdateBy(updateBy);
        // 恢复条目;
        entity.setIsDelete((byte) 0);
        count.addAndGet(permitDao.updateByPrimaryKeySelective(entity));
        return count.get();
    }

    public Integer recoverPermitList(List<Integer> ids, String updateBy) throws StatusException {
        // 记录更新条目数;
        AtomicInteger count = new AtomicInteger();
        // 将List中所有的Entity更新;
        for (Integer id : ids) {
            // 更新计数;
            count.addAndGet(recoverPermit(id, updateBy));
        }
        return count.get();
    }
}
