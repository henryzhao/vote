package im.vec.vote.service;

import cc.eamon.open.status.StatusException;
import im.vec.vote.dao.CandidateVoterDao;
import im.vec.vote.entity.CandidateVoter;
import im.vec.vote.entity.CandidateVoterDetailMapper;
import im.vec.vote.entity.CandidateVoterExample;
import im.vec.vote.entity.CandidateVoterKey;
import im.vec.vote.entity.CandidateVoterPostMapper;
import im.vec.vote.entity.CandidateVoterSimpleMapper;
import im.vec.vote.entity.CandidateVoterUpdateMapper;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@Service
public class CandidateVoterService {
  @Autowired
  private CandidateVoterDao candidateVoterDao;

  /**
   * 分页计数函数 */
  private CandidateVoterExample initPageRowQueryFilter(Long page, Integer rows) {
    CandidateVoterExample example = new CandidateVoterExample();
    example.setOrderByClause("updateTime desc");
    CandidateVoterExample.Criteria criteria = example.createCriteria();
    criteria.andIsDeleteEqualTo((byte) 0);
    if (page > 0) {
      Long offset = (page - 1L) * rows;
      example.setLimit(rows);
      example.setOffset(offset);
    }
    return example;
  }

  /**
   * 参数过滤函数 */
  private CandidateVoterExample initQueryFilter(Long page, Integer rows, Date createTime,
      Date updateTime, String updateBy, Byte isDelete, Integer candidateId, String createBy) {
    CandidateVoterExample example = new CandidateVoterExample();
    example.setOrderByClause("updateTime desc");
    CandidateVoterExample.Criteria criteria = example.createCriteria();
    if(createTime!=null) {
      criteria.andCreateTimeEqualTo(createTime);
    }
    if(updateTime!=null) {
      criteria.andUpdateTimeEqualTo(updateTime);
    }
    if(updateBy!=null && !updateBy.equals("")) {
      criteria.andUpdateByEqualTo(updateBy);
    }
    if(isDelete!=null) {
      criteria.andIsDeleteEqualTo(isDelete);
    }
    if(candidateId!=null) {
      criteria.andCandidateIdEqualTo(candidateId);
    }
    if(createBy!=null && !createBy.equals("")) {
      criteria.andCreateByEqualTo(createBy);
    }
    if (page > 0) {
      Long offset = (page - 1L) * rows;
      example.setLimit(rows);
      example.setOffset(offset);
    }
    return example;
  }

  public Long getCandidateVoterCount() throws StatusException {
    return candidateVoterDao.countByExample(initPageRowQueryFilter(0L, 0));
  }

  public Long getCandidateVoterFilterCount(Date createTime, Date updateTime, String updateBy,
      Byte isDelete, Integer candidateId, String createBy) throws StatusException {
    return candidateVoterDao.countByExample(initQueryFilter(0L, 0, createTime, updateTime, updateBy, isDelete, candidateId, createBy));
  }

  /**
   * 联合查询函数 */
  private CandidateVoterExample initCandidateIdQueryFilter(Integer candidateId) {
    CandidateVoterExample example = new CandidateVoterExample();
    CandidateVoterExample.Criteria criteria = example.createCriteria();
    criteria.andIsDeleteEqualTo((byte) 0);
    criteria.andCandidateIdEqualTo(candidateId);
    return example;
  }

  public Long getCandidateVoterCountByCandidateId(Integer candidateId) throws StatusException {
    return candidateVoterDao.countByExample(initCandidateIdQueryFilter(candidateId));
  }

  public CandidateVoter getCandidateVoterByPrimaryKey(CandidateVoterKey key) throws
      StatusException {
    return candidateVoterDao.selectByPrimaryKey(key);
  }

  public Map<String, Object> getCandidateVoterSimpleMapByPrimaryKey(CandidateVoterKey key) throws
      StatusException {
    CandidateVoter entity = candidateVoterDao.selectByPrimaryKey(key);
    if(entity == null) throw new StatusException("ENTITY_NULL");
    return CandidateVoterSimpleMapper.getMapWithExtra(entity,entity.getCandidateId(), entity.getCreateBy());
  }

  public Map<String, Object> getCandidateVoterDetailMapByPrimaryKey(CandidateVoterKey key) throws
      StatusException {
    CandidateVoter entity = candidateVoterDao.selectByPrimaryKey(key);
    if(entity == null) throw new StatusException("ENTITY_NULL");
    return CandidateVoterDetailMapper.getMapWithExtra(entity,entity.getCandidateId(), entity.getCreateBy());
  }

  public List<Map<String, Object>> getCandidateVoterSimpleMapList(Long page, Integer rows) throws
      StatusException {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (CandidateVoter entity: candidateVoterDao.selectByExample(initPageRowQueryFilter(page, rows))) {
      entityMapList.add(CandidateVoterSimpleMapper.getMapWithExtra(entity,entity.getCandidateId(), entity.getCreateBy()));
    }
    return entityMapList;
  }

  public List<Map<String, Object>> getCandidateVoterDetailMapList(Long page, Integer rows) throws
      StatusException {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (CandidateVoter entity: candidateVoterDao.selectByExample(initPageRowQueryFilter(page, rows))) {
      entityMapList.add(CandidateVoterDetailMapper.getMapWithExtra(entity,entity.getCandidateId(), entity.getCreateBy()));
    }
    return entityMapList;
  }

  public Map<String, Object> getCandidateVoterMapByCandidateId(Integer candidateId) throws StatusException {
    List<Map<String, Object>> entityMapList = getCandidateVoterMapListByCandidateId(candidateId);
    if (entityMapList.size() == 0) return new LinkedHashMap<>();
    else return entityMapList.get(0);
  }

  public List<Map<String, Object>> getCandidateVoterMapListByCandidateId(Integer candidateId) throws StatusException {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (CandidateVoter entity: candidateVoterDao.selectByExample(initCandidateIdQueryFilter(candidateId))) {
      entityMapList.add(CandidateVoterDetailMapper.getMap(entity));
    }
    return entityMapList;
  }

  public Integer deleteCandidateVoterByCandidateId(Integer candidateId, String updateBy) throws StatusException {
    AtomicInteger count = new AtomicInteger();
    for (CandidateVoter entity: candidateVoterDao.selectByExample(initCandidateIdQueryFilter((candidateId)))) {
      count.addAndGet(deleteCandidateVoter(entity, updateBy));
    }
    return count.get();
  }

  public Map<String, Object> postCandidateVoter(CandidateVoterPostMapper postMapper,
      String createBy, String updateBy) throws StatusException {
    // 记录上传条目数;
    AtomicInteger count = new AtomicInteger();
    // 从上传数据中拿出记录;
    CandidateVoter entity = postMapper.getEntity();
    // 记录主键;
    entity.setCandidateId(postMapper.candidateId);
    // 记录创建者;
    entity.setCreateBy(createBy);
    // 记录更新者;
    entity.setUpdateBy(updateBy);
    // 持久化对象;
    try {
      count.addAndGet(candidateVoterDao.insertSelective(entity));
    }
    catch (Exception e) {
      throw new StatusException("CANT_VOTE");
    }
    return CandidateVoterDetailMapper.getMap(entity);
  }

  public List<Map<String, Object>> postCandidateVoterList(List<CandidateVoterPostMapper> postMappers,
      String createBy, String updateBy) throws StatusException {
    // 记录上传条目数;
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    // 将List中所有的Entity均上传;
    for (CandidateVoterPostMapper postMapper: postMappers) {
      // 插入计数;
      entityMapList.add(postCandidateVoter(postMapper, createBy, updateBy));
    }
    return entityMapList;
  }

  public Map<String, Object> updateCandidateVoter(CandidateVoterUpdateMapper updateMapper,
      String updateBy) throws StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 从更新数据中拿出记录;
    CandidateVoter entity = updateMapper.getEntity();
    // 记录主键;
    entity.setCandidateId(updateMapper.candidateId);
    // 记录更新者;
    entity.setUpdateBy(updateBy);
    try {
      count.addAndGet(candidateVoterDao.updateByPrimaryKeySelective(entity));
    }
    catch (Exception e) {
      throw new StatusException("ENTITY_NULL");
    }
    return CandidateVoterDetailMapper.getMap(entity);
  }

  public List<Map<String, Object>> updateCandidateVoterList(List<CandidateVoterUpdateMapper> updateMappers,
      String updateBy) throws StatusException {
    // 记录更新条目数;
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    // 将List中所有的Entity更新;
    for (CandidateVoterUpdateMapper updateMapper: updateMappers) {
      // 更新计数;
      entityMapList.add(updateCandidateVoter(updateMapper, updateBy));
    }
    return entityMapList;
  }

  public Integer deleteCandidateVoter(CandidateVoterKey key, String updateBy) throws
      StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 从更新数据中拿出记录;
    CandidateVoter entity = new CandidateVoter(key);
    // 记录更新者;
    entity.setUpdateBy(updateBy);
    // 删除条目;
    entity.setIsDelete((byte) 1);
    count.addAndGet(candidateVoterDao.updateByPrimaryKeySelective(entity));
    return count.get();
  }

  public Integer deleteCandidateVoterList(List<CandidateVoterKey> keys, String updateBy) throws
      StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 将List中所有的Entity更新;
    for (CandidateVoterKey key: keys) {
      // 更新计数;
      count.addAndGet(deleteCandidateVoter(key, updateBy));
    }
    return count.get();
  }

  public Integer recoverCandidateVoter(CandidateVoterKey key, String updateBy) throws
      StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 从更新数据中拿出记录;
    CandidateVoter entity = new CandidateVoter(key);
    // 记录更新者;
    entity.setUpdateBy(updateBy);
    // 恢复条目;
    entity.setIsDelete((byte) 0);
    count.addAndGet(candidateVoterDao.updateByPrimaryKeySelective(entity));
    return count.get();
  }

  public Integer recoverCandidateVoterList(List<CandidateVoterKey> keys, String updateBy) throws
      StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 将List中所有的Entity更新;
    for (CandidateVoterKey key: keys) {
      // 更新计数;
      count.addAndGet(recoverCandidateVoter(key, updateBy));
    }
    return count.get();
  }
}
