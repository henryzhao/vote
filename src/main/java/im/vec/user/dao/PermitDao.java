package im.vec.user.dao;

import im.vec.base.MyBatisBaseDao;
import im.vec.user.entity.Permit;
import im.vec.user.entity.PermitDetailMapper;
import im.vec.user.entity.PermitExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PermitDao继承基类
 */
@Repository
public interface PermitDao extends MyBatisBaseDao<Permit, Integer, PermitExample> {

    List<Permit> selectPermitsByRoleId(@Param("roleId") int roleId);

    List<PermitDetailMapper> selectPermitsByRoleIdWithExtra(@Param("roleId") int roleId);

}