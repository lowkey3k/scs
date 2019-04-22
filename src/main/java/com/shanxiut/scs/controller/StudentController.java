package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.StudentService;
import com.shanxiut.scs.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午9:28
 **/
@RequestMapping("/student")
@RestController
//@Authorize(resources = "STUDENT")
public class StudentController extends AbstractCrudController<Student,Long, StudentService> {

    @Autowired
    private UserService userService;


    @PutMapping("/update")
    @AccessLogger("更新学生信息")
    public ResponseMessage<Student> update(@RequestBody Student student){
        User user = userService.findById(student.getUser().getId());
        user.setUsername(student.getUser().getUsername());
        user.setNumber(student.getUser().getNumber());
        userService.updateById(user);
        return ResponseMessage.ok(service.updateById(student));
    }

    @Override
    @AccessLogger("添加学生")
    @PostMapping
    public ResponseMessage<Student> insert(@RequestBody Student student){
        Md5Hash md5Hash = new Md5Hash(student.getUser().getPassword(), student.getUser().getNumber());
        student.getUser().setPassword(md5Hash.toString());
        student.getUser().setSalt(student.getUser().getNumber());
        return ResponseMessage.ok(service.insert(student));
    }
}
