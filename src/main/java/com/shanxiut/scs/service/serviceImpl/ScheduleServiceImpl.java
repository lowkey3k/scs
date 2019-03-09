package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.ScheduleDao;
import com.shanxiut.scs.entity.Schedule;
import com.shanxiut.scs.service.ScheduleService;
import org.springframework.stereotype.Service;

/**
 * Created by hliu on 2019/3/9.
 */
@Service
public class ScheduleServiceImpl extends SuperServiceImpl<Long,ScheduleDao,Schedule> implements ScheduleService{

}
