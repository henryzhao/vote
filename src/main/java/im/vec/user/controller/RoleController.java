package im.vec.user.controller;

import cc.eamon.open.permission.Limit;
import cc.eamon.open.permission.annotation.Permission;
import cc.eamon.open.permission.annotation.PermissionLimit;
import cc.eamon.open.status.Status;
import cc.eamon.open.status.StatusBaseController;
import cc.eamon.open.status.StatusCode;
import im.vec.user.entity.RolePostMapper;
import im.vec.user.entity.RoleUpdateMapper;
import im.vec.user.service.RoleService;
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
    value = "系统-用户-角色管理",
    tags = "系统-用户-角色管理"
)
@RestController
@RequestMapping("/role")
@Permission("sys_role")
public class RoleController extends StatusBaseController {
  @Autowired
  private RoleService roleService;

  @ApiOperation(
      value = "获取系统-用户-角色简要列表 PASS: 测试内容 删除后查询",
      notes = "获取系统-用户-角色简要列表 PASS: 测试内容 删除后查询"
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
  public Status getRoleSimpleMapList(@RequestParam(required = false, defaultValue = "0") Long page, @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.getRoleSimpleMapList(page, rows),
        roleService.getRoleCount()
        );
  }

  @ApiOperation(
      value = "获取系统-用户-角色详情列表 PASS: 测试内容 删除后查询 item数量",
      notes = "获取系统-用户-角色详情列表 PASS: 测试内容 删除后查询 item数量"
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
  public Status getRoleDetailMapList(@RequestParam(required = false, defaultValue = "0") Long page, @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.getRoleDetailMapList(page, rows),
        roleService.getRoleCount()
        );
  }

  @ApiOperation(
      value = "通过id获取系统-用户-角色简要信息 PASS: 测试内容 删除后查询",
      notes = "通过id获取系统-用户-角色简要信息 PASS: 测试内容 删除后查询"
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
  public Status getRoleSimpleMap(@PathVariable Integer entityId) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.getRoleSimpleMapById(entityId),
        "查询成功");
  }

  @ApiOperation(
      value = "通过id获取系统-用户-角色详细信息 PASS: 测试内容 删除后查询（屏蔽内容） item数量",
      notes = "通过id获取系统-用户-角色详细信息 PASS: 测试内容 删除后查询（屏蔽内容） item数量"
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
  public Status getRoleDetailMap(@PathVariable Integer entityId) throws Exception {
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.getRoleDetailMapById(entityId),
        "查询成功");
  }

  @ApiOperation(
      value = "发布系统-用户-角色信息",
      notes = "发布系统-用户-角色信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_CREATE)
  public Status postRoleMap(@RequestBody RolePostMapper entityPostMapper, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.postRole(entityPostMapper, userName, userName),
        "发布成功");
  }

  @ApiOperation(
      value = "发布一组系统-用户-角色信息",
      notes = "发布一组系统-用户-角色信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "list",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_CREATE)
  public Status postRoleMapList(@RequestBody ArrayList<RolePostMapper> entityPostMapperList, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.postRoleList(entityPostMapperList, userName, userName),
        "发布成功");
  }

  @ApiOperation(
      value = "更新系统-用户-角色信息",
      notes = "更新系统-用户-角色信息"
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
  public Status updateRoleMap(@RequestBody RoleUpdateMapper entityUpdateMapper, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.updateRole(entityUpdateMapper, userName),
        "更新成功");
  }

  @ApiOperation(
      value = "更新一组系统-用户-角色信息",
      notes = "更新一组系统-用户-角色信息"
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
  public Status updateRoleMapList(@RequestBody ArrayList<RoleUpdateMapper> entityUpdateMapperList, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.updateRoleList(entityUpdateMapperList, userName),
        "更新成功");
  }

  @ApiOperation(
      value = "删除系统-用户-角色信息",
      notes = "删除系统-用户-角色信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "delete",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_DELETE)
  public Status deleteRoleById(Integer id, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.deleteRole(id, userName),
        "删除成功");
  }

  @ApiOperation(
      value = "删除一组系统-用户-角色信息",
      notes = "删除一组系统-用户-角色信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "delete/list",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_DELETE)
  public Status deleteRoleByIdList(ArrayList<Integer> ids, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.deleteRoleList(ids, userName),
        "删除成功");
  }

  @ApiOperation(
      value = "恢复系统-用户-角色信息",
      notes = "恢复系统-用户-角色信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "recover",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_DELETE)
  public Status recoverRoleById(Integer id, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.recoverRole(id, userName),
        "恢复成功");
  }

  @ApiOperation(
      value = "恢复一组系统-用户-角色信息",
      notes = "恢复一组系统-用户-角色信息"
  )
  @Transactional(
      rollbackFor = Exception.class
  )
  @RequestMapping(
      value = "recover/list",
      method = RequestMethod.POST
  )
  @ResponseBody
  @PermissionLimit(limits = Limit.OPERATION_DELETE)
  public Status recoverRoleByIdList(ArrayList<Integer> ids, HttpServletRequest request) throws Exception {
    String userName = (String) request.getAttribute("userName");
    return new Status(
        true,
        StatusCode.getCode("SUCCESS"),
        roleService.recoverRoleList(ids, userName),
        "恢复成功");
  }
}
