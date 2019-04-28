package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.annotation.Authorize;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.entity.Teacher;
import com.shanxiut.scs.service.TeacherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Override
    @AccessLogger("添加老师")
    @PostMapping
    @Authorize(resources = "INSERT")
    public ResponseMessage<Teacher> insert(@RequestBody Teacher teacher){
        Md5Hash md5Hash = new Md5Hash(teacher.getUser().getPassword(), teacher.getUser().getNumber());
        teacher.getUser().setPassword(md5Hash.toString());
        teacher.getUser().setSalt(teacher.getUser().getNumber());
        return ResponseMessage.ok(service.insert(teacher));
    }

}
