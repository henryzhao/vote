package im.vec.vote.dao;

import im.vec.vote.entity.CandidateVoter;
import im.vec.vote.entity.CandidateVoterExample;
import im.vec.vote.entity.CandidateVoterKey;
import org.springframework.stereotype.Repository;

/**
 * CandidateVoterDao继承基类
 */
@Repository
public interface CandidateVoterDao extends MyBatisBaseDao<CandidateVoter, CandidateVoterKey, CandidateVoterExample> {
}