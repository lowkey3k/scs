package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.StudentDao;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午9:25
 **/
@Service
public class StudentServiceImpl extends SuperServiceImpl<Long, StudentDao,Student> implements StudentService {

}
