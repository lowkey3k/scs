package com.shanxiut.scs.service;

import com.shanxiut.scs.entity.Student;

import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午9:24
 **/
public interface StudentService extends SuperService<Student,Long> {

    public Student updateById(Student student);

    List<Student> findByUsernameAndNumber(String number, String username);

    Student findByUserID(Long userID);
}
