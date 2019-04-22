package im.vec.user.dao;

import im.vec.base.MyBatisBaseDao;
import im.vec.user.entity.Role;
import im.vec.user.entity.RoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleDao继承基类
 */
@Repository
public interface RoleDao extends MyBatisBaseDao<Role, Integer, RoleExample> {


    List<Role> selectRolesByUserId(@Param("userId") int userId);

}