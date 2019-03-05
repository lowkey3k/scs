package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.Department;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.DepartmentService;
import com.shanxiut.scs.service.StudentService;
import com.shanxiut.scs.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentController extends SuperController<Department,Long, DepartmentService> {


    @Override
    public <S extends SuperService<Department, Long>> S getService() {
        return null;
    }
}
