package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.Course;
import com.shanxiut.scs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/3/5.
 */
@RequestMapping("/course")
@RestController
public class CourseController extends SuperController<Course,CourseService>{

}
