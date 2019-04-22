package im.vec.vote.controller;

import cc.eamon.open.permission.annotation.Permission;
import cc.eamon.open.permission.annotation.PermissionLimit;
import cc.eamon.open.status.Status;
import cc.eamon.open.status.StatusBaseController;
import cc.eamon.open.status.StatusCode;
import im.vec.vote.entity.CandidateVoterKey;
import im.vec.vote.entity.CandidateVoterPostMapper;
import im.vec.vote.entity.CandidateVoterUpdateMapper;
import im.vec.vote.service.CandidateVoterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@Api(
    value = "投票人管理",
    tags = "投票人管理"
)
@RestController
@RequestMapping("/candidate/voter")
@Permission
public class CandidateVoterController extends StatusBaseController {
  @Autowired
  private CandidateVoterService candidateVoterService;

  @ApiOperation(
      value = "获取投票人简要列表",
      notes = "获取投票人简要列表"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit
  public Status getCandidateVoterSimpleMapList(@RequestParam(required = false, defaultValue = "0") Long page,
      @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.getCandidateVoterSimpleMapList(page, rows),
        candidateVoterService.getCandidateVoterCount()
        );
  }

  @ApiOperation(
      value = "获取投票人详情列表",
      notes = "获取投票人详情列表"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "detail",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit
  public Status getCandidateVoterDetailMapList(@RequestParam(required = false, defaultValue = "0") Long page,
      @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.getCandidateVoterDetailMapList(page, rows),
        candidateVoterService.getCandidateVoterCount()
        );
  }

  @ApiOperation(
      value = "通过主键获取投票人简要信息",
      notes = "通过主键获取投票人简要信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "key",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit
  public Status getCandidateVoterSimpleMap(CandidateVoterKey key) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.getCandidateVoterSimpleMapByPrimaryKey(key),
        "查询成功");
  }

  @ApiOperation(
      value = "通过主键获取投票人详细信息",
      notes = "通过主键获取投票人详细信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "key/detail",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit
  public Status getCandidateVoterDetailMap(CandidateVoterKey key) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.getCandidateVoterDetailMapByPrimaryKey(key),
        "查询成功");
  }

  @ApiOperation(
      value = "发布投票人信息",
      notes = "发布投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit
  public Status postCandidateVoterMap(@RequestBody CandidateVoterPostMapper entityPostMapper,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.postCandidateVoter(entityPostMapper, userName, userName),
        "发布成功");
  }

  @ApiOperation(
      value = "发布一组投票人信息",
      notes = "发布一组投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "list",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit
  public Status postCandidateVoterMapList(@RequestBody ArrayList<CandidateVoterPostMapper> entityPostMapperList,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.postCandidateVoterList(entityPostMapperList, userName, userName),
        "发布成功");
  }

  @ApiOperation(
      value = "更新投票人信息",
      notes = "更新投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "key",
      method = RequestMethod.PUT
  )
  @ResponseBody
  @PermissionLimit
  public Status updateCandidateVoterMap(@RequestBody CandidateVoterUpdateMapper entityUpdateMapper,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.updateCandidateVoter(entityUpdateMapper, userName),
        "更新成功");
  }

  @ApiOperation(
      value = "更新一组投票人信息",
      notes = "更新一组投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "list",
      method = RequestMethod.PUT
  )
  @ResponseBody
  @PermissionLimit
  public Status updateCandidateVoterMapList(@RequestBody ArrayList<CandidateVoterUpdateMapper> entityUpdateMapperList,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.updateCandidateVoterList(entityUpdateMapperList, userName),
        "更新成功");
  }

  @ApiOperation(
      value = "删除投票人信息",
      notes = "删除投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "key",
      method = RequestMethod.DELETE
  )
  @ResponseBody
  @PermissionLimit
  public Status deleteCandidateVoterByPrimaryKey(@RequestBody CandidateVoterKey entityKey,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.deleteCandidateVoter(entityKey, userName),
        "删除成功");
  }

  @ApiOperation(
      value = "删除一组投票人信息",
      notes = "删除一组投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "list",
      method = RequestMethod.DELETE
  )
  @ResponseBody
  @PermissionLimit
  public Status deleteCandidateVoterByPrimaryKeyList(@RequestBody ArrayList<CandidateVoterKey> keys,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.deleteCandidateVoterList(keys, userName),
        "删除成功");
  }

  @ApiOperation(
      value = "恢复投票人信息",
      notes = "恢复投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "key/recover",
      method = RequestMethod.DELETE
  )
  @ResponseBody
  @PermissionLimit
  public Status recoverCandidateVoterByPrimaryKey(@RequestBody CandidateVoterKey entityKey,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.recoverCandidateVoter(entityKey, userName),
        "恢复成功");
  }

  @ApiOperation(
      value = "恢复一组投票人信息",
      notes = "恢复一组投票人信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "recover/list",
      method = RequestMethod.DELETE
  )
  @ResponseBody
  @PermissionLimit
  public Status recoverCandidateVoterByPrimaryKeyList(@RequestBody ArrayList<CandidateVoterKey> keys,
      HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        candidateVoterService.recoverCandidateVoterList(keys, userName),
        "恢复成功");
  }
}
