package com.shanxiut.scs.controller;

import com.shanxiut.scs.common.util.ShiroUtils;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * @Description IndexController:登录注册接口
 * @Author LiHaitao
 * @Date 2018/8/23 15:08
 * @UpdateUser
 * @UpdateDescrip
 * @UpdateDate
 * @Version 1.0.0
 **/
@RequestMapping("/index")
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ResponseMessage reg(@RequestBody User user) {
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), user.getUsername());
        user.setPassword(md5Hash.toString());
        user.setCode(UUID.randomUUID().toString());
        user.setSalt(user.getUsername());
        return ResponseMessage.ok(userService.insert(user));
    }

    @RequestMapping("/login")
    public ResponseMessage login(String name, String password, String code, String rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        Subject subject = ShiroUtils.getSubject();
        token.setRememberMe(rememberMe == null ? false : true);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            String simpleName = e.getClass().getSimpleName();
            if ("UnknownAccountException".equals(simpleName)) {
                return ResponseMessage.error("The user does not exist");
            } else if ("IncorrectCredentialsException".equals(simpleName)) {
                return ResponseMessage.error("Incorrect password");
            } else if ("UnsupportedTokenException".equals(simpleName)) {
                return ResponseMessage.error("token exception");
            }
        }
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            return ResponseMessage.ok("Login successfully");
        }
        return ResponseMessage.error("Login failure");

    }

    @RequestMapping("/logout")
    public ResponseMessage logout() {
        // 注销登录
        SecurityUtils.getSubject().logout();
        return ResponseMessage.ok();
    }


}