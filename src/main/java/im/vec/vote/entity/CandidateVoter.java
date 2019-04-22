package im.vec.vote.entity;

import cc.eamon.open.mapping.mapper.Mapper;
import cc.eamon.open.mapping.mapper.MapperExtra;
import cc.eamon.open.mapping.mapper.MapperIgnore;
import cc.eamon.open.mapping.mapper.MapperModify;
import java.io.Serializable;
import java.lang.Byte;
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
 * Email: eamon@eamon.cc */
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
        "simple", 
        "detail",
        "simple", 
        "detail"},
    name = {
        "candidateId", 
        "candidateId", 
        "candidateId", 
        "candidateId",
        "createBy", 
        "createBy"},
    type = {
        "java.lang.Integer", 
        "java.lang.Integer", 
        "java.lang.Integer", 
        "java.lang.Integer",
        "java.lang.String", 
        "java.lang.String"},
    list = {
        false, 
        false, 
        false, 
        false,
        false, 
        false}
)
public class CandidateVoter extends CandidateVoterKey implements Serializable {
  /**
   * 创建时间 */
  @MapperIgnore({"post", "update"})
  @MapperModify(
      value = {"post", "update"},
      modify = {"modifyTime", "modifyTime"},
      recover = {"recoverTime", "recoverTime"}
  )
  private Date createTime;

  /**
   * 更新时间 */
  @MapperIgnore({"post", "update"})
  @MapperModify(
      value = {"post", "update"},
      modify = {"modifyTime", "modifyTime"},
      recover = {"recoverTime", "recoverTime"}
  )
  private Date updateTime;

  /**
   * 更新操作人 */
  @MapperIgnore({"post", "update"})
  private String updateBy;

  /**
   * 删除状态 */
  @MapperIgnore({"post", "update"})
  private Byte isDelete;

  public CandidateVoter(CandidateVoterKey key) {
    super(key.getCandidateId(),key.getCreateBy());
  }

  public Long modifyTime(Date date) {
    if (date == null) return null;
    return date.getTime();
  }

  public Date recoverTime(Long time) {
    if (time == null) return null;
    return new Date(time);
  }
}
