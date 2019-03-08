package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.Teacher;
import com.shanxiut.scs.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description TeacherController:
 * @date 2019/3/8 14:07
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController extends AbstractCrudController<Teacher,Long,TeacherService> {
}
