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
        value = {"detail", "detail", "post","update"},
        list = true,
        name = {"roles", "permits", "roles", "roles"},
        type = {
                "java.util.Map",
                "java.util.Map",
                "java.lang.Integer",
                "java.lang.Integer"
        }
)
public class User {
    /**
     * 用户Id
     */
    @MapperIgnore({"post"})
    private Integer id;

    /**
     * 账号名
     */
    private String account;

    /**
     * 密码
     */
    @MapperIgnore({"simple", "detail"})
    private String password;

    /**
     * 盐
     */
    @MapperIgnore({"simple", "detail", "post", "update"})
    private String salt;

    /**
     * 令牌
     */
    @MapperIgnore({"post", "update"})
    private String token;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 职位
     */
    private String position;

    /**
     * 创建时间
     */
    @MapperIgnore({"post", "update"})
    @MapperModify(
            value = {"post", "update", "simple", "detail", "default"},
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
            value = {"post", "update", "simple", "detail", "default"},
            modify = {"modifyTime", "modifyTime", "modifyTime", "modifyTime", "modifyTime"},
            recover = {"recoverTime", "recoverTime", "recoverTime", "recoverTime", "recoverTime"}
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
