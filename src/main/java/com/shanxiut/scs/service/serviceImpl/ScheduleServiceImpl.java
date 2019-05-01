package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.common.response.ResponseMessage;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hliu on 2019/3/9.
 */
@Service
public class ScheduleServiceImpl extends SuperServiceImpl<Long,ScheduleDao,Schedule> implements ScheduleService{

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public ResponseMessage<Schedule> chooseSchedule(Schedule schedule) {
        Schedule schedule1 = getDao().getOne(schedule.getId());
        if(schedule1==null) throw new ObjectNotFoundException(null,"排课信息不存在");
        List<Student> studentOnTable = schedule1.getStudents();
        for(Student stu : studentOnTable){
            if(stu.getId().equals(schedule.getStudents().get(0).getId()))
                return ResponseMessage.error("您已选择该课程，请勿重复选择！");
        }
        List<Student> students = schedule.getStudents();
        List<Student> students1 = schedule1.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student;
            try {
                student = studentService.getDao().getOne(students.get(i).getId());

            } catch (NumberFormatException e) {
                continue;
            }
            if (student != null) {
                students1.add(student);
            }
        }
        //将课程中实际选课人数增1
        this.getDao().save(schedule1);
        this.getDao().flush();
        return ResponseMessage.ok();
    }

    @Override
    public List<Schedule> findScheduleByStudentID(Long studentID) {
        return scheduleDao.findSchedulesById(studentID);
    }
}
