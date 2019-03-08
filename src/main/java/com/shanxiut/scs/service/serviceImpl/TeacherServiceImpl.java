package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.TeacherDao;
import com.shanxiut.scs.entity.Teacher;
import com.shanxiut.scs.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description TeacherServiceImpl:
 * @date 2019/3/8 14:08
 **/
@Service
public class TeacherServiceImpl extends SuperServiceImpl<Long,TeacherDao,Teacher> implements TeacherService {
}
