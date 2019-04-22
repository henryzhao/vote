package im.vec.vote.entity;

import cc.eamon.open.mapping.mapper.Mapper;
import cc.eamon.open.mapping.mapper.MapperExtra;
import cc.eamon.open.mapping.mapper.MapperIgnore;
import cc.eamon.open.mapping.mapper.MapperModify;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Mapper({"post", "update", "simple", "detail"})
@MapperExtra(
        value = {
                "post",
                "update",
                "detail",
                "detail"
        },
        name = {
                "votes",
                "votes",
                "votes",
                "voteNum"
        },
        type = {
                "im.vec.vote.entity.CandidateVoterPostMapper",
                "im.vec.vote.entity.CandidateVoterPostMapper",
                "java.util.Map",
                "java.lang.Long"
        },
        list = {
                true,
                true,
                true,
                false}
)
public class Candidate implements Serializable {
    /**
     * 候选人id
     */
    private Integer id;

    /**
     * 候选人姓名
     */
    private String name;

    /**
     * 候选人标语
     */
    private String des;

    /**
     * 创建人
     */
    @MapperIgnore({"post", "update"})
    private String createBy;

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
     * 修改人
     */
    @MapperIgnore({"post", "update"})
    private String updateBy;

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
