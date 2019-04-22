package im.vec.vote.dao;

import im.vec.base.MyBatisBaseDao;
import im.vec.vote.entity.Candidate;
import im.vec.vote.entity.CandidateExample;
import org.springframework.stereotype.Repository;

/**
 * CandidateDao继承基类
 */
@Repository
public interface CandidateDao extends MyBatisBaseDao<Candidate, Integer, CandidateExample> {
}