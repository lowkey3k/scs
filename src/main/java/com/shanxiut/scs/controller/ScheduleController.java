package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.Schedule;
import com.shanxiut.scs.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hliu on 2019/3/9.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController extends AbstractCrudController<Schedule,Long,ScheduleService>{

}
