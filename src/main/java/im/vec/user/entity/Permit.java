package im.vec.user.entity;

import cc.eamon.open.mapping.mapper.Mapper;
import cc.eamon.open.mapping.mapper.MapperExtra;
import cc.eamon.open.mapping.mapper.MapperIgnore;
import cc.eamon.open.mapping.mapper.MapperModify;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 */
@Setter
@Getter
@ToString
@Mapper({"post", "update", "simple", "detail"})
@MapperExtra(
        value = "detail",
        name = "operation",
        type = "java.lang.String"
)
public class Permit {
    /**
     * 主键
     */
    @MapperIgnore({"post"})
    private Integer id;

    /**
     * 系统识别键
     */
    @MapperIgnore({"update"})
    private String sysId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限群组
     */
    private String group;

    /**
     * 权限备注
     */
    private String note;

    /**
     * 创建时间
     */
    @MapperIgnore({"post", "update"})
    @MapperModify(
            value = {"post", "update", "default"},
            modify = {"modifyTime", "modifyTime", "modifyTime", "modifyTime", "modifyTime"},
            recover = {"recoverTime", "recoverTime", "recoverTime", "recoverTime", "recoverTime"}
    )
    private Date createTime;

    /**
     * 创建操作人
     */
    @MapperIgnore({"post", "update"})
    private String createBy;

    /**
     * 更新时间
     */
    @MapperIgnore({"post", "update"})
    @MapperModify(
            value = {"post", "update"},
            modify = {"modifyTime", "modifyTime"},
            recover = {"recoverTime", "recoverTime"}
    )
    private Date updateTime;

    /**
     * 更新操作人
     */
    @MapperIgnore({"post", "update"})
    private String updateBy;

    /**
     * 删除状态
     */
    @MapperIgnore({"post", "update"})
    private Byte isDelete;

    public Long modifyTime(Date date) {
        if (date == null) return null;
        return date.getTime();
    }

    public Date recoverTime(Long time) {
        if (time == null) return null;
        return new Date(time);
    }
}
