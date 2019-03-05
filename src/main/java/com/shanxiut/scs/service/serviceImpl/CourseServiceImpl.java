package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.CourseDao;
import com.shanxiut.scs.entity.Course;
import com.shanxiut.scs.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/3/5.
 */
@Service
public class CourseServiceImpl extends SuperServiceImpl<Long,CourseDao,Course> implements CourseService{

}
