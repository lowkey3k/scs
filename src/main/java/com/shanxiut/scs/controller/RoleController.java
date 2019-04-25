package com.shanxiut.scs.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Sets;
import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.auth.service.ResourceService;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.auth.vo.RoleVO;
import com.shanxiut.scs.common.param.CrudParam;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.common.util.CrudParamUtil;
import com.shanxiut.scs.common.util.UpdateTool;
import com.shanxiut.scs.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description RoleController:
 * @date 2019/3/8 14:11
 **/
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractCrudController<Role,Long,RoleService> {


    @Autowired
    private ResourceService resourceService;

    @GetMapping("/ownerAndAllRole/{id}")
    @AccessLogger("List结果查询")
//    @Authorize(resources = AuthConstant.Resource.QUERY)
    public ResponseMessage<List<RoleVO>> select(@PathVariable Long id) {
        return ResponseMessage.ok(service.selectOwnerRoleByUserId(id));
    }

    @AccessLogger("更新角色信息")
    @PutMapping("/update")
    public ResponseMessage updateUser(@RequestBody Role role){
        if (role.getId()==null){
            return ResponseMessage.error("没有此角色");
        }
        Role byId = service.findById(role.getId());
        role.setResources(byId.getResources());

        Set<Resource> collect=Sets.newHashSet();
        if (CollectionUtil.isNotEmpty(role.getResources())){
                collect = role.getResources().stream().map(resource -> {
                Resource resource1 = resourceService.findById(resource.getId());
                return resource1;
            }).collect(Collectors.toSet());

            role.setResources(collect);
        }else {
            role.setResources(byId.getResources());
        }
        UpdateTool.copyNullProperties(byId,role);
        return ResponseMessage.ok(service.updateById(role));
    }


    @AccessLogger("添加资源")
    @PutMapping("/addResource/{id}")
    public ResponseMessage addRole(@PathVariable Long id,@RequestBody List<Long> resources){
        Role role=service.findById(id);
        Set<Resource> collect= Sets.newHashSet();
        if (CollectionUtil.isNotEmpty(resources)){
            collect = resources.stream().map(resourceId -> {
                Resource byId = resourceService.findById(resourceId);
                return byId;
            }).collect(Collectors.toSet());
        }
        role.setResources(collect);
        return ResponseMessage.ok(service.updateById(role));
    }



}
