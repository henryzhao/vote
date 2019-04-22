package im.vec.user.dao;

import im.vec.base.MyBatisBaseDao;
import im.vec.user.entity.UserRole;
import im.vec.user.entity.UserRoleExample;
import im.vec.user.entity.UserRoleKey;
import org.springframework.stereotype.Repository;

/**
 * UserRoleDao继承基类
 */
@Repository
public interface UserRoleDao extends MyBatisBaseDao<UserRole, UserRoleKey, UserRoleExample> {

    int recoverByUserId(int userId);

}