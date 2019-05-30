package com.shanxiut.scs.service.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.shanxiut.scs.common.exception.RestException;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.common.validator.Assert;
import com.shanxiut.scs.dao.ScheduleDao;
import com.shanxiut.scs.entity.Schedule;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.ScheduleService;
import com.shanxiut.scs.service.StudentService;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hliu on 2019/3/9.
 */
@Service
public class ScheduleServiceImpl extends SuperServiceImpl<Long, ScheduleDao, Schedule> implements ScheduleService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public ResponseMessage<Schedule> chooseSchedule(Schedule schedule) {
        //
        Schedule noSchedule = getDao().getOne(schedule.getId());
        //校验排课信息是否存在
        Assert.isNull(noSchedule, "排课信息不存在");
        List<Student> students = noSchedule.getStudents();
        List<Student> studentList = schedule.getStudents();

        Assert.isEmpty(studentList, "没有要选择课程的学生。。。");

        if (CollectionUtil.isNotEmpty(students)) {
            for (Student stu : students) {
                if (stu.getId().equals(studentList.get(0).getId()))
                    return ResponseMessage.error("您已选择该课程，请勿重复选择！");
            }
        }
        Schedule save=new Schedule();
        for (Student student : studentList) {
            Student stu;
            //如果不做此判断，这里的stu会是一个未知的对象地址，并且不是null，
            if (studentService.getDao().existsById(student.getId())){
                stu = studentService.getDao().getOne(student.getId());
            }else {
                return ResponseMessage.error("你不是学生");
            }
            if (ObjectUtil.isNotNull(stu)) {
                students.add(stu);

            }
            //将课程中实际选课人数增1
        }
        save = this.getDao().save(noSchedule);
        this.getDao().flush();
        return ResponseMessage.ok(save);
    }

    @Override
    public List<Schedule> findScheduleByStudentID(Long studentID) {
        return scheduleDao.findSchedulesById(studentID);
    }

    @Override
    public List<Schedule> findSchedulesByTeacherID(Long teacherID) {
        return scheduleDao.findSchedulesByTeacherId(teacherID);
    }


}
