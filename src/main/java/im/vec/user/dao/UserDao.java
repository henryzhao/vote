package im.vec.user.dao;

import im.vec.base.MyBatisBaseDao;
import im.vec.user.entity.User;
import im.vec.user.entity.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao继承基类
 */
@Repository
public interface UserDao extends MyBatisBaseDao<User, Integer, UserExample> {

    User selectByAccount(@Param("account") String account);

    List<User> selectUsersByRoleId(@Param("roleId") Integer roleId);

}