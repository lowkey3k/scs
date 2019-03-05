package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.DepartmentDao;
import com.shanxiut.scs.entity.Department;
import com.shanxiut.scs.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/5 下午10:43
 **/
@Service
public class DepartmentServiceImpl extends SuperServiceImpl<Long, DepartmentDao, Department> implements DepartmentService {
}
