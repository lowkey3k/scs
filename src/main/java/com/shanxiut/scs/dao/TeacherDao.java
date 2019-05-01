package com.shanxiut.scs.dao;

import com.shanxiut.scs.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description TeacherDao:
 * @date 2019/3/8 14:08
 **/
@Repository
public interface TeacherDao extends SuperDao<Teacher,Long> {

    @Query(value="select * from scs_teacher where user_id=?1",nativeQuery = true)
    Teacher findByUserId(Long userID);
}
