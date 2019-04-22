package im.vec.user.controller;

import cc.eamon.open.permission.Limit;
import cc.eamon.open.permission.annotation.Permission;
import cc.eamon.open.permission.annotation.PermissionLimit;
import cc.eamon.open.status.Status;
import cc.eamon.open.status.StatusCode;
import im.vec.user.entity.PermitPostMapper;
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
 * Email: eamon@eamon.cc
 * Time: 2019-01-21 00:46:39
 */
@Api(
        value = "系统-用户-权限保护接口管理",
        tags = "系统-用户-权限保护接口管理"
)
@RestController
@RequestMapping("/permit")
@Permission("sys_role")
public class PermitProtectedController {

    @Autowired
    private PermitService permitService;

    @ApiOperation(
            value = "发布权限信息",
            notes = "发布权限信息"
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
    public Status postPermitMap(@RequestBody PermitPostMapper entityPostMapper, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                permitService.postPermit(entityPostMapper, userName, userName),
                "发布成功");
    }

    @ApiOperation(
            value = "发布一组权限信息",
            notes = "发布一组权限信息"
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
    public Status postPermitMapList(@RequestBody ArrayList<PermitPostMapper> entityPostMapperList, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                permitService.postPermitList(entityPostMapperList, userName, userName),
                "发布成功");
    }


    @ApiOperation(
            value = "删除权限信息",
            notes = "删除权限信息"
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
    public Status deletePermitById(Integer id, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                permitService.deletePermit(id, userName),
                "删除成功");
    }

    @ApiOperation(
            value = "删除一组权限信息",
            notes = "删除一组权限信息"
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
    public Status deletePermitByIdList(@RequestBody ArrayList<Integer> ids, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                permitService.deletePermitList(ids, userName),
                "删除成功");
    }

    @ApiOperation(
            value = "恢复权限信息",
            notes = "恢复权限信息"
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
    public Status recoverPermitById(Integer id, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                permitService.recoverPermit(id, userName),
                "恢复成功");
    }

    @ApiOperation(
            value = "恢复一组权限信息",
            notes = "恢复一组权限信息"
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
    public Status recoverPermitByIdList(@RequestBody ArrayList<Integer> ids, HttpServletRequest request) throws Exception {
        String userName = (String) request.getAttribute("userName");
        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                permitService.recoverPermitList(ids, userName),
                "恢复成功");
    }

}
