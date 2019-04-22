package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.common.util.UpdateTool;
import com.shanxiut.scs.dao.StudentDao;
import com.shanxiut.scs.entity.Department;
import com.shanxiut.scs.entity.GradeClass;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.GradeClassService;
import com.shanxiut.scs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午9:25
 **/
@Service
public class StudentServiceImpl extends SuperServiceImpl<Long, StudentDao, Student> implements StudentService {

    @Autowired
    private GradeClassService gradeClassService;

    @Override
    public Student updateById(Student student) {
        Student one = getDao().getOne(student.getId());
        GradeClass gradeClass=gradeClassService.getDao().getOne(student.getGradeClass().getId());
        student.setGradeClass(gradeClass);
        User user = one.getUser();
        UpdateTool.copyNullProperties(user,student.getUser());
        UpdateTool.copyNullProperties(one,student);
        return this.getDao().save(student);
    }


}
