package im.vec.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_user_role
 * @author 
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserRole extends UserRoleKey implements Serializable {


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建操作人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新操作人
     */
    private String updateBy;

    /**
     * 删除状态
     */
    private Byte isDelete;



    public UserRole(Integer userId, Integer roleId) {
        super(userId, roleId);
    }


    public UserRole(Byte isDelete, String updateBy) {
        this.isDelete = isDelete;
        this.updateBy = updateBy;
    }

    public UserRole(Integer userId, Integer roleId, String createBy, String updateBy) {
        super(userId, roleId);
        this.createBy = createBy;
        this.updateBy = updateBy;
    }
}