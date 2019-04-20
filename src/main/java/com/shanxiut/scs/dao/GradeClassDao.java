package com.shanxiut.scs.dao;

import com.shanxiut.scs.entity.GradeClass;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHaitao
 * @description GradeClassDao:
 * @date 2019/3/8 13:58
 **/
@Repository
public interface GradeClassDao extends SuperDao<GradeClass,Long> {

   public List<GradeClass> findByDepartment_Id(Long id);
}
