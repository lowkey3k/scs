package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午9:28
 **/
@RequestMapping("/student")
@RestController
public class StudentController extends SuperController<Student, StudentService> {



}
