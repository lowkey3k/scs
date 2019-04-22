package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.common.util.UpdateTool;
import com.shanxiut.scs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

/**
 * @author LiHaitao
 * @description UserController:
 * @date 2019/3/8 14:36
 **/
@RestController
@RequestMapping("/user")
public class UserController extends AbstractCrudController<User,Long,UserService> {
    @Autowired
    private UserService userService;

    @AccessLogger("更新用户信息")
    @PutMapping("/update")
    public ResponseMessage updateUser(@RequestBody User user){
        if (user.getId()==null){
            return ResponseMessage.error("没有此用户");
        }
        User oldUser = userService.findById(user.getId());
        user.setRoles(oldUser.getRoles());
        UpdateTool.copyNullProperties(oldUser,user);
        return ResponseMessage.ok(userService.updateById(user));
    }
}
