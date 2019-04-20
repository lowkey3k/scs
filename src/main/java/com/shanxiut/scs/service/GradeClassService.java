package com.shanxiut.scs.service;

import com.shanxiut.scs.entity.GradeClass;

import java.util.List;

/**
 * @author LiHaitao
 * @description GradeClassService:
 * @date 2019/3/8 13:58
 **/
public interface GradeClassService extends SuperService<GradeClass,Long> {

    public List<GradeClass> findByDepartmentId(Long deptId);
}
