package im.vec.user.controller;

import cc.eamon.open.permission.annotation.Permission;
import cc.eamon.open.permission.annotation.PermissionLimit;
import cc.eamon.open.status.Status;
import cc.eamon.open.status.StatusBaseController;
import cc.eamon.open.status.StatusCode;
import cc.eamon.open.status.StatusException;
import im.vec.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
@Permission
public class UserPublicController extends StatusBaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "系统-用户-用户登录 PASS 测试内容：删除系统-用户-用户后登录",
            notes = "系统-用户-用户登录 PASS 测试内容：删除系统-用户-用户后登录")
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public Status login(
            @RequestParam String account,
            @RequestParam String password,
            HttpServletResponse response
    ) throws Exception {
        Map<String, Object> loginMap = userService.login(account, password);

        response.setHeader("token", loginMap.get("token").toString());
        response.setHeader("userId", loginMap.get("id").toString());
        Cookie cookie = new Cookie("token", loginMap.get("token").toString());
        Cookie cookieUser = new Cookie("userId", loginMap.get("id").toString());
        cookie.setPath("/");
        cookieUser.setPath("/");
        response.addCookie(cookie);
        response.addCookie(cookieUser);

        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                loginMap,
                "登录成功"
        );
    }

    @ApiOperation(
            value = "系统-用户-用户登出 PASS 测试内容：非登录状态登出",
            notes = "系统-用户-用户登出 PASS 测试内容：非登录状态登出")
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    @PermissionLimit
    public Status logout(
            HttpServletRequest request
    ) throws Exception {
        String userId = request.getHeader("userId");//header方式
        if (null == userId || userId.isEmpty()) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("userId")) {
                    userId = c.getValue();
                    break;
                }
            }
        }
        if (userId == null) throw new StatusException("USER_NULL");

        return new Status(
                true,
                StatusCode.getCode("SUCCESS"),
                userService.logout(Integer.parseInt(userId)),
                "登出成功"
        );
    }

}
