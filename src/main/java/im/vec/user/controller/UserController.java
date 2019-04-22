package im.vec.user.controller;

import cc.eamon.open.permission.Limit;
import cc.eamon.open.permission.annotation.Permission;
import cc.eamon.open.permission.annotation.PermissionLimit;
import cc.eamon.open.status.Status;
import cc.eamon.open.status.StatusBaseController;
import cc.eamon.open.status.StatusCode;
import im.vec.user.entity.UserPostMapper;
import im.vec.user.entity.UserUpdateMapper;
import im.vec.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 */
@Api(
        value = "系统-用户-用户管理",
        tags = "系统-用户-用户管理"
)
@RestController
@RequestMapping("/user")
@Permission("sys_account")
public class UserController extends StatusBaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "获取系统-用户-用户简要列表 PASS 测试内容：删除后拉取 不在列表中显示",
            notes = "获取系统-用户-用户简要列表 PASS 测试内容：删除后拉取 不在列表中显示"
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
    public Status getUserSimpleMapList(@RequestParam(required = false, defaultValue = "0") Long page, @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.getUserSimpleMapList(page, rows),
                userService.getUserCount()
        );
    }

    @ApiOperation(
            value = "获取系统-用户-用户详情列表 PASS 测试内容：删除后拉取 不在列表中显示",
            notes = "获取系统-用户-用户详情列表 PASS 测试内容：删除后拉取 不在列表中显示"
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
    public Status getUserDetailMapList(@RequestParam(required = false, defaultValue = "0") Long page, @RequestParam(required = false, defaultValue = "10") Integer rows) throws Exception {
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.getUserDetailMapList(page, rows),
                userService.getUserCount()
        );
    }

    @ApiOperation(
            value = "通过id获取系统-用户-用户简要信息 PASS 测试内容：删除后拉取",
            notes = "通过id获取系统-用户-用户简要信息 PASS 测试内容：删除后拉取"
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
    public Status getUserSimpleMap(@PathVariable Integer entityId) throws Exception {
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.getUserSimpleMapById(entityId),
                "查询成功");
    }

    @ApiOperation(
            value = "通过id获取系统-用户-用户详细信息 PASS 测试内容：删除后拉取 屏蔽角色权限",
            notes = "通过id获取系统-用户-用户详细信息 PASS 测试内容：删除后拉取 屏蔽角色权限"
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
    public Status getUserDetailMap(@PathVariable Integer entityId) throws Exception {
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.getUserDetailMapById(entityId),
                "查询成功");
    }

    @ApiOperation(
            value = "发布系统-用户-用户信息 PASS 测试内容：重复发布",
            notes = "发布系统-用户-用户信息 PASS 测试内容：重复发布"
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
    public Status postUserMap(@RequestBody UserPostMapper entityPostMapper, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.postUser(entityPostMapper, userName, userName),
                "发布成功");
    }

    @ApiOperation(
            value = "发布一组系统-用户-用户信息 PASS 测试内容：重复发布",
            notes = "发布一组系统-用户-用户信息 PASS 测试内容：重复发布"
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
    public Status postUserMapList(@RequestBody ArrayList<UserPostMapper> entityPostMapperList, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.postUserList(entityPostMapperList, userName, userName),
                "发布成功");
    }

    @ApiOperation(
            value = "更新系统-用户-用户信息 PASS 测试内容：不存在更新",
            notes = "更新系统-用户-用户信息 PASS 测试内容：不存在更新"
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
    public Status updateUserMap(@RequestBody UserUpdateMapper entityUpdateMapper, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.updateUser(entityUpdateMapper, userName),
                "更新成功");
    }

    @ApiOperation(
            value = "更新一组系统-用户-用户信息 PASS 测试内容：不存在更新",
            notes = "更新一组系统-用户-用户信息 PASS 测试内容：不存在更新"
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
    public Status updateUserMapList(@RequestBody ArrayList<UserUpdateMapper> entityUpdateMapperList, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.updateUserList(entityUpdateMapperList, userName),
                "更新成功");
    }

    @ApiOperation(
            value = "删除系统-用户-用户信息 PASS 测试内容：删除系统-用户-用户以及系统-用户-用户角色关系",
            notes = "删除系统-用户-用户信息 PASS 测试内容：删除系统-用户-用户以及系统-用户-用户角色关系"
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
    public Status deleteUserById(Integer id, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.deleteUser(id, userName),
                "删除成功");
    }

    @ApiOperation(
            value = "删除一组系统-用户-用户信息 PASS 测试内容：删除系统-用户-用户以及系统-用户-用户角色关系",
            notes = "删除一组系统-用户-用户信息 PASS 测试内容：删除系统-用户-用户以及系统-用户-用户角色关系"
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
    public Status deleteUserByIdList(@RequestBody ArrayList<Integer> ids, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.deleteUserList(ids, userName),
                "删除成功");
    }

    @ApiOperation(
            value = "恢复系统-用户-用户信息 PASS 测试内容：恢复系统-用户-用户以及系统-用户-用户角色关系",
            notes = "恢复系统-用户-用户信息 PASS 测试内容：恢复系统-用户-用户以及系统-用户-用户角色关系"
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
    public Status recoverUserById(Integer id, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.recoverUser(id, userName),
                "恢复成功");
    }

    @ApiOperation(
            value = "恢复一组系统-用户-用户信息 PASS 测试内容：恢复系统-用户-用户以及系统-用户-用户角色关系",
            notes = "恢复一组系统-用户-用户信息 PASS 测试内容：恢复系统-用户-用户以及系统-用户-用户角色关系"
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
    public Status recoverUserByIdList(@RequestBody ArrayList<Integer> ids, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.recoverUserList(ids, userName),
                "恢复成功");
    }

}
