package com.shanxiut.scs.controller;

import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description UserController:
 * @date 2019/3/8 14:36
 **/
@RestController
@RequestMapping
public class UserController extends AbstractCrudController<User,Long,UserService> {
}
