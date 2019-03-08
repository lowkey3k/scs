package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.Course;
import com.shanxiut.scs.service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description CourseController:
 * @date 2019/3/8 14:03
 **/
@RestController
@RequestMapping("/course")
public class CourseController extends AbstractCrudController<Course,Long,CourseService> {





}
