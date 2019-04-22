package im.vec.vote.service;

import cc.eamon.open.status.StatusException;
import im.vec.vote.dao.CandidateDao;
import im.vec.vote.entity.Candidate;
import im.vec.vote.entity.CandidateDetailMapper;
import im.vec.vote.entity.CandidateExample;
import im.vec.vote.entity.CandidatePostMapper;
import im.vec.vote.entity.CandidateSimpleMapper;
import im.vec.vote.entity.CandidateUpdateMapper;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@Service
public class CandidateService {
  @Autowired
  private CandidateDao candidateDao;

  @Autowired
  private CandidateVoterService candidateVoterService;

  /**
   * 分页计数函数 */
  private CandidateExample initPageRowQueryFilter(Long page, Integer rows) {
    CandidateExample example = new CandidateExample();
    example.setOrderByClause("updateTime desc");
    CandidateExample.Criteria criteria = example.createCriteria();
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
  private CandidateExample initQueryFilter(Long page, Integer rows, Integer id, String name,
      String des, String createBy, Date createTime, String updateBy, Date updateTime,
      Byte isDelete) {
    CandidateExample example = new CandidateExample();
    example.setOrderByClause("updateTime desc");
    CandidateExample.Criteria criteria = example.createCriteria();
    if(id!=null) {
      criteria.andIdEqualTo(id);
    }
    if(name!=null && !name.equals("")) {
      criteria.andNameEqualTo(name);
    }
    if(des!=null && !des.equals("")) {
      criteria.andDesEqualTo(des);
    }
    if(createBy!=null && !createBy.equals("")) {
      criteria.andCreateByEqualTo(createBy);
    }
    if(createTime!=null) {
      criteria.andCreateTimeEqualTo(createTime);
    }
    if(updateBy!=null && !updateBy.equals("")) {
      criteria.andUpdateByEqualTo(updateBy);
    }
    if(updateTime!=null) {
      criteria.andUpdateTimeEqualTo(updateTime);
    }
    if(isDelete!=null) {
      criteria.andIsDeleteEqualTo(isDelete);
    }
    if (page > 0) {
      Long offset = (page - 1L) * rows;
      example.setLimit(rows);
      example.setOffset(offset);
    }
    return example;
  }

  public Long getCandidateCount() throws StatusException {
    return candidateDao.countByExample(initPageRowQueryFilter(0L, 0));
  }

  public Long getCandidateFilterCount(Integer id, String name, String des, String createBy,
      Date createTime, String updateBy, Date updateTime, Byte isDelete) throws StatusException {
    return candidateDao.countByExample(initQueryFilter(0L, 0, id, name, des, createBy, createTime, updateBy, updateTime, isDelete));
  }

  public Candidate getCandidateByPrimaryKey(Integer key) throws StatusException {
    return candidateDao.selectByPrimaryKey(key);
  }

  public Map<String, Object> getCandidateSimpleMapByPrimaryKey(Integer key) throws StatusException {
    Candidate entity = candidateDao.selectByPrimaryKey(key);
    if(entity == null) throw new StatusException("ENTITY_NULL");
    return CandidateSimpleMapper.getMap(entity);
  }

  public Map<String, Object> getCandidateDetailMapByPrimaryKey(Integer key) throws StatusException {
    Candidate entity = candidateDao.selectByPrimaryKey(key);
    if(entity == null) throw new StatusException("ENTITY_NULL");
    return CandidateDetailMapper.getMapWithExtra(entity,candidateVoterService.getCandidateVoterMapListByCandidateId(entity.getId()),
            candidateVoterService.getCandidateVoterFilterCount(null, null, null, (byte)0, entity.getId(), null));
  }

  public List<Map<String, Object>> getCandidateSimpleMapList(Long page, Integer rows) throws
      StatusException {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (Candidate entity: candidateDao.selectByExample(initPageRowQueryFilter(page, rows))) {
      entityMapList.add(CandidateSimpleMapper.getMap(entity));
    }
    return entityMapList;
  }

  public List<Map<String, Object>> getCandidateDetailMapList(Long page, Integer rows) throws
      StatusException {
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    for (Candidate entity: candidateDao.selectByExample(initPageRowQueryFilter(page, rows))) {
      entityMapList.add(CandidateDetailMapper.getMapWithExtra(entity,candidateVoterService.getCandidateVoterMapListByCandidateId(entity.getId()),
              candidateVoterService.getCandidateVoterFilterCount(null, null, null, (byte)0, entity.getId(), null)));
    }
    return entityMapList;
  }

  public Map<String, Object> postCandidate(CandidatePostMapper postMapper, String createBy,
      String updateBy) throws StatusException {
    // 记录上传条目数;
    AtomicInteger count = new AtomicInteger();
    // 从上传数据中拿出记录;
    Candidate entity = postMapper.getEntity();
    // 记录创建者;
    entity.setCreateBy(createBy);
    // 记录更新者;
    entity.setUpdateBy(updateBy);
    // 持久化对象;
    try {
      count.addAndGet(candidateDao.insertSelective(entity));
    }
    catch (Exception e) {
      throw new StatusException("ENTITY_EXIST");
    }
    List<Map<String, Object>> votesList = new ArrayList<>();
    if (postMapper.votes != null && postMapper.votes.size()!=0)  {
      postMapper.votes.forEach(mapper -> mapper.candidateId = entity.getId());
      votesList = candidateVoterService.postCandidateVoterList(postMapper.votes, createBy, updateBy);
    }
    return CandidateDetailMapper.getMapWithExtra(entity,votesList, 0L);
  }

  public List<Map<String, Object>> postCandidateList(List<CandidatePostMapper> postMappers,
      String createBy, String updateBy) throws StatusException {
    // 记录上传条目数;
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    // 将List中所有的Entity均上传;
    for (CandidatePostMapper postMapper: postMappers) {
      // 插入计数;
      entityMapList.add(postCandidate(postMapper, createBy, updateBy));
    }
    return entityMapList;
  }

  public Map<String, Object> updateCandidate(CandidateUpdateMapper updateMapper, String updateBy)
      throws StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 从更新数据中拿出记录;
    Candidate entity = updateMapper.getEntity();
    // 记录更新者;
    entity.setUpdateBy(updateBy);
    try {
      count.addAndGet(candidateDao.updateByPrimaryKeySelective(entity));
    }
    catch (Exception e) {
      throw new StatusException("ENTITY_NULL");
    }
    List<Map<String, Object>> votesList = new ArrayList<>();
    if (updateMapper.votes != null && updateMapper.votes.size()!=0)  {
      candidateVoterService.deleteCandidateVoterByCandidateId(entity.getId() ,updateBy);
      updateMapper.votes.forEach(mapper -> mapper.candidateId = entity.getId());
      votesList = candidateVoterService.postCandidateVoterList(updateMapper.votes, updateBy, updateBy);
    }
    return CandidateDetailMapper.getMapWithExtra(entity,votesList,0L);
  }

  public List<Map<String, Object>> updateCandidateList(List<CandidateUpdateMapper> updateMappers,
      String updateBy) throws StatusException {
    // 记录更新条目数;
    List<Map<String, Object>> entityMapList = new ArrayList<>();
    // 将List中所有的Entity更新;
    for (CandidateUpdateMapper updateMapper: updateMappers) {
      // 更新计数;
      entityMapList.add(updateCandidate(updateMapper, updateBy));
    }
    return entityMapList;
  }

  public Integer deleteCandidate(Integer key, String updateBy) throws StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 从更新数据中拿出记录;
    Candidate entity = new Candidate();
    // 记录更新者;
    entity.setId(key);
    entity.setUpdateBy(updateBy);
    // 删除条目;
    entity.setIsDelete((byte) 1);
    count.addAndGet(candidateDao.updateByPrimaryKeySelective(entity));
    candidateVoterService.deleteCandidateVoterByCandidateId(entity.getId() ,updateBy);
    return count.get();
  }

  public Integer deleteCandidateList(List<Integer> keys, String updateBy) throws StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 将List中所有的Entity更新;
    for (Integer key: keys) {
      // 更新计数;
      count.addAndGet(deleteCandidate(key, updateBy));
    }
    return count.get();
  }

  public Integer recoverCandidate(Integer key, String updateBy) throws StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 从更新数据中拿出记录;
    Candidate entity = new Candidate();
    // 记录更新者;
    entity.setId(key);
    entity.setUpdateBy(updateBy);
    // 恢复条目;
    entity.setIsDelete((byte) 0);
    count.addAndGet(candidateDao.updateByPrimaryKeySelective(entity));
    return count.get();
  }

  public Integer recoverCandidateList(List<Integer> keys, String updateBy) throws StatusException {
    // 记录更新条目数;
    AtomicInteger count = new AtomicInteger();
    // 将List中所有的Entity更新;
    for (Integer key: keys) {
      // 更新计数;
      count.addAndGet(recoverCandidate(key, updateBy));
    }
    return count.get();
  }
}
