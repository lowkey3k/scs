package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.Department;
import com.shanxiut.scs.service.DepartmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午9:28
 **/
@RequestMapping("/department")
@RestController
public class DepartmentController extends AbstractCrudController<Department, Long, DepartmentService> {



}
