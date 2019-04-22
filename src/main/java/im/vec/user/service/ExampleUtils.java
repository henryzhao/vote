package im.vec.user.service;

import im.vec.user.entity.*;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2019-01-21 00:36:40
 */
public class ExampleUtils {



    public static UserExample initUserIdsQueryFilter(List<Integer> ids) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);
        criteria.andIdIn(ids);
        return example;
    }

    public static RoleExample initRoleIdsQueryFilter(List<Integer> ids) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);
        criteria.andIdIn(ids);
        return example;
    }

    public static PermitExample initPermitIdsQueryFilter(List<Integer> ids){
        PermitExample example = new PermitExample();
        PermitExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((byte) 0);
        criteria.andIdIn(ids);
        return example;
    }

    public static UserRoleExample initUserRoleByUserIdQueryFilter(Integer userId){
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIsDeleteEqualTo((byte) 0);
        return example;
    }

    public static UserRoleExample initUserRoleByRoleIdQueryFilter(Integer roleId){
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andIsDeleteEqualTo((byte) 0);
        return example;
    }

    public static RolePermitExample initRolePermitByRoleIdQueryFilter(Integer roleId){
        RolePermitExample example = new RolePermitExample();
        RolePermitExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andIsDeleteEqualTo((byte) 0);
        return example;
    }

}
