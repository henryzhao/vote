package im.vec.user.dao;

import im.vec.base.MyBatisBaseDao;
import im.vec.user.entity.RolePermit;
import im.vec.user.entity.RolePermitExample;
import im.vec.user.entity.RolePermitKey;
import org.springframework.stereotype.Repository;

/**
 * RolePermitDao继承基类
 */
@Repository
public interface RolePermitDao extends MyBatisBaseDao<RolePermit, RolePermitKey, RolePermitExample> {
}