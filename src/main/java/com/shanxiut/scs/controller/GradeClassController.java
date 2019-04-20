package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.entity.GradeClass;
import com.shanxiut.scs.service.GradeClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiHaitao
 * @description GradeClassController:
 * @date 2019/3/8 14:01
 **/
@RestController
@RequestMapping("/grade_class")
public class GradeClassController extends AbstractCrudController<GradeClass,Long,GradeClassService> {

    @GetMapping("/getbyDepartment/{deptId}")
    @AccessLogger("根据部门id查找班级")
    public ResponseMessage<List<GradeClass>> selectbyDeptId(@PathVariable("deptId")Long deptId){
        return ResponseMessage.ok(getService().findByDepartmentId(deptId));
    }
}
