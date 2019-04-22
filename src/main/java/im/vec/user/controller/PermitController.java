package im.vec.user.controller;

import cc.eamon.open.permission.Limit;
import cc.eamon.open.permission.annotation.Permission;
import cc.eamon.open.permission.annotation.PermissionLimit;
import cc.eamon.open.status.Status;
import cc.eamon.open.status.StatusBaseController;
import cc.eamon.open.status.StatusCode;
import im.vec.user.entity.PermitUpdateMapper;
import im.vec.user.service.PermitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Author: eamon
 * Email: eamon@eamon.cc */
@Api(
    value = "系统-用户-权限管理",
    tags = "系统-用户-权限管理"
)
@RestController
@RequestMapping("/permit")
@Permission("sys_role")
public class PermitController extends StatusBaseController {
  @Autowired
  private PermitService permitService;

  @ApiOperation(
      value = "获取系统-用户-权限简要列表",
      notes = "获取系统-用户-权限简要列表"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_RETRIEVE)
  public Status getPermitSimpleMapList(@RequestParam(required = false, defaultValue = "0") Long page, @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        permitService.getPermitSimpleMapList(page, rows),
        permitService.getPermitCount()
        );
  }

  @ApiOperation(
      value = "获取系统-用户-权限详情列表",
      notes = "获取系统-用户-权限详情列表"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "detail",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_RETRIEVE)
  public Status getPermitDetailMapList(@RequestParam(required = false, defaultValue = "0") Long page, @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        permitService.getPermitDetailMapList(page, rows),
        permitService.getPermitCount()
        );
  }

  @ApiOperation(
      value = "通过id获取系统-用户-权限简要信息",
      notes = "通过id获取系统-用户-权限简要信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "{entityId}",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_RETRIEVE)
  public Status getPermitSimpleMap(@PathVariable Integer entityId) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        permitService.getPermitSimpleMapById(entityId),
        "查询成功");
  }

  @ApiOperation(
      value = "通过id获取系统-用户-权限详细信息",
      notes = "通过id获取系统-用户-权限详细信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "{entityId}/detail",
      method = RequestMethod.GET
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_RETRIEVE)
  public Status getPermitDetailMap(@PathVariable Integer entityId) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        permitService.getPermitDetailMapById(entityId),
        "查询成功");
  }

  @ApiOperation(
      value = "更新系统-用户-权限信息",
      notes = "更新系统-用户-权限信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "update",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_UPDATE)
  public Status updatePermitMap(@RequestBody PermitUpdateMapper entityUpdateMapper, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        permitService.updatePermit(entityUpdateMapper, userName),
        "更新成功");
  }

  @ApiOperation(
      value = "更新一组系统-用户-权限信息",
      notes = "更新一组系统-用户-权限信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "update/list",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_UPDATE)
  public Status updatePermitMapList(@RequestBody ArrayList<PermitUpdateMapper> entityUpdateMapperList, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        permitService.updatePermitList(entityUpdateMapperList, userName),
        "更新成功");
  }

}
