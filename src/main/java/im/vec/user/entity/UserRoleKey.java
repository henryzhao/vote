package im.vec.user.entity;

import lombok.*;

import java.io.Serializable;

/**
 * sys_user_role
 * @author 
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleKey implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;

}