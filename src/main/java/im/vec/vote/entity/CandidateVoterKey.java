package im.vec.vote.entity;

import cc.eamon.open.mapping.mapper.MapperIgnore;
import java.io.Serializable;
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
 * Email: eamon@eamon.cc */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CandidateVoterKey implements Serializable {
  /**
   * 候选人id */
  private Integer candidateId;

  /**
   * 创建操作人 */
  @MapperIgnore({"post", "update"})
  private String createBy;

  public Long modifyTime(Date date) {
    if (date == null) return null;
    return date.getTime();
  }

  public Date recoverTime(Long time) {
    if (time == null) return null;
    return new Date(time);
  }
}
