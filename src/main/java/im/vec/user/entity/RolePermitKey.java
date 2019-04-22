package im.vec.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * sys_role_permit
 * @author 
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermitKey implements Serializable {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permitId;

}