package com.shanxiut.scs.service;

import com.shanxiut.scs.entity.Teacher;

/**
 * @author LiHaitao
 * @description TeacherService:
 * @date 2019/3/8 14:07
 **/
public interface TeacherService extends SuperService<Teacher,Long> {
    Teacher findByUserID(Long UserID);
}
