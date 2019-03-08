package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.GradeClassDao;
import com.shanxiut.scs.entity.GradeClass;
import com.shanxiut.scs.service.GradeClassService;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description GradeClassServiceImpl:
 * @date 2019/3/8 13:59
 **/
@Service
public class GradeClassServiceImpl extends SuperServiceImpl<Long,GradeClassDao,GradeClass> implements GradeClassService {
}
