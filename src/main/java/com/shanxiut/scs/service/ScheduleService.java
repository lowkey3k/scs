package com.shanxiut.scs.service;

import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.entity.Schedule;
import com.shanxiut.scs.entity.Student;

import java.util.List;

/**
 * Created by Administrator on 2019/3/9.
 */
public interface ScheduleService extends SuperService<Schedule,Long> {

    public ResponseMessage chooseSchedule(Schedule schedule);

    public List<Schedule> findScheduleByStudentID(Long studentID);

    public List<Schedule> findSchedulesByTeacherID(Long teacherID);
}
