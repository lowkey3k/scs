package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.annotation.Authorize;
import com.shanxiut.scs.auth.constant.AuthConstant;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.entity.Schedule;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.ScheduleService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hliu on 2019/3/9.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends AbstractCrudController<Schedule,Long,ScheduleService>{

    @Autowired
    private ScheduleService scheduleService;

    @AccessLogger("学生选课")
    @PostMapping
    @RequestMapping("/chooseSchedule")
    public ResponseMessage<Schedule> chooseSchedule(@RequestBody Schedule schedule){
        return scheduleService.chooseSchedule(schedule);
    }

    @AccessLogger("根据学生ID查找所对应的选课信息")
    @GetMapping("/getScheduleByStudentID/{StudentID}")
    public ResponseMessage<List<Schedule>> getScheduleByStudentID(@PathVariable("StudentID")Long StudentID){
        return ResponseMessage.ok(scheduleService.findScheduleByStudentID(StudentID));
    }

    @AccessLogger("根据教师ID查找所对应的排课信息")
    @GetMapping("/getScheduleByTeacherID")
    public ResponseMessage<List<Schedule>> getScheduleByTeacherID(@RequestParam("teacherID") Long teacherID){
        return ResponseMessage.ok(scheduleService.findSchedulesByTeacherID(teacherID));
    }

}
