package im.vec.user.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_role_permit
 *
 * @author
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RolePermit extends RolePermitKey implements Serializable {
    /**
     * 操作字符
     */
    private String operation;

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


    public RolePermit(Integer roleId, Integer permitId, String operation, String createBy, String updateBy) {
        super(roleId, permitId);
        this.operation = operation;
        this.createBy = createBy;
        this.updateBy = updateBy;
    }

    public RolePermit(Byte isDelete, String updateBy) {
        this.isDelete = isDelete;
        this.updateBy = updateBy;
    }

    public RolePermit(Integer roleId, Integer permitId, Byte isDelete) {
        super(roleId, permitId);
        this.isDelete = isDelete;
    }


}