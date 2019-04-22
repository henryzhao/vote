package im.vec.user.entity;

import cc.eamon.open.mapping.mapper.Mapper;
import cc.eamon.open.mapping.mapper.MapperExtra;
import cc.eamon.open.mapping.mapper.MapperIgnore;
import cc.eamon.open.mapping.mapper.MapperModify;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Mapper({"simple", "detail", "user", "post", "update"})
@MapperExtra(
        value = {"post", "post", "update", "update", "detail", "detail", "detail", "simple"},
        list = { true, true, true, true, true, true, false, false},
        name = {"users", "permits", "users", "permits", "users", "permits", "quantity", "quantity"},
        type = {
                "java.lang.Integer",
                "java.lang.String",
                "java.lang.Integer",
                "java.lang.String",
                "java.util.Map",
                "java.util.Map",
                "java.lang.Long",
                "java.lang.Long"
        }
)
@NoArgsConstructor
public class Role {
    /**
     * 主键
     */
    @MapperIgnore({"post"})
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建时间
     */
    @MapperIgnore({"post", "update"})
    @MapperModify(
            value = {"post", "update"},
            modify = {"modifyTime", "modifyTime"},
            recover = {"recoverTime", "recoverTime"}
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
