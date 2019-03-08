package com.shanxiut.scs.controller;

import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description RoleController:
 * @date 2019/3/8 14:11
 **/
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractCrudController<Role,Long,RoleService> {
}
