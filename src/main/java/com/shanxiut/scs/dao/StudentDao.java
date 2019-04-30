package com.shanxiut.scs.dao;

import com.shanxiut.scs.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午2:04
 **/
@Repository
public interface StudentDao extends SuperDao<Student, Long> {

//    @Modifying
//    @Transactional
//    @Query("update scs_student s set" +
//            " s.sex =#{student.sex},s.birthday=:#{#student.birthday},s.age=:#{#student.age}," +
//            "s.phone=:#{#student.phone},s.email=:#{#student.email},s.id_number=:#{#student.idNumber}," +
//            "s.grade_class=:#{#student.gradeClass}")
//    public int update(@Param("student") Student student);
    List<Student> findByUser_NumberOrUser_Username(String number,String username);

    Student findByUser_Id(Long id);
}
