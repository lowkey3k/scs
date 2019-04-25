package com.shanxiut.scs.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.common.param.CrudParam;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.common.util.CrudParamUtil;
import com.shanxiut.scs.common.util.UpdateTool;
import com.shanxiut.scs.service.UserService;
import com.shanxiut.scs.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private RoleService roleService;

    @AccessLogger("更新用户信息")
    @PutMapping("/update")
    public ResponseMessage updateUser(@RequestBody User user){
        if (user.getId()==null){
            return ResponseMessage.error("没有此用户");
        }
        User oldUser = userService.findById(user.getId());

        Set<Role> oldroles = user.getRoles();
        Set<Role> roles= Sets.newHashSet();

        if (CollectionUtil.isNotEmpty(oldroles)){
            for (Role oldRole:oldroles) {
                Role role = roleService.findById(oldRole.getId());
                roles.add(role);
            }
            user.setRoles(roles);
        }else {
            user.setRoles(oldUser.getRoles());
        }
        UpdateTool.copyNullProperties(oldUser,user);
        return ResponseMessage.ok(userService.updateById(user));
    }

    @AccessLogger("添加角色")
    @PutMapping("/addRole/{id}")
    public ResponseMessage addRole(@PathVariable Long id,@RequestBody List<Long> roles){
        User user=userService.findById(id);
        Set<Role> collect= Sets.newHashSet();
       if (CollectionUtil.isNotEmpty(roles)){
           collect = roles.stream().map(roleId -> {
               Role byId = roleService.findById(roleId);
               return byId;
           }).collect(Collectors.toSet());
       }
        user.setRoles(collect);
        return ResponseMessage.ok(userService.updateById(user));
    }



    @GetMapping("/list")
    @AccessLogger("List结果查询")
//    @Authorize(resources = AuthConstant.Resource.QUERY)
    public ResponseMessage<List<UserVO>> select(CrudParam crudParam, HttpServletRequest request) {
        CrudParamUtil.padding(crudParam, request);
        List<User> users = this.getService().findAll(crudParam);

        return ResponseMessage.ok(users.stream().map(this::entityToModel).collect(Collectors.toList()));
    }


    private UserVO entityToModel(User user){
        UserVO userVO=new UserVO();

        BeanUtil.copyProperties(user,userVO);
        String roleStr="";
        if (user.getRoles()!=null){
            List<String> roles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
            Joiner on = Joiner.on(",");
            roleStr=on.join(roles);
        }
       userVO.setRoleStr(roleStr);
        return userVO;
    }

}
