package com.shanxiut.scs.auth.service.serviceImpl;

import com.shanxiut.scs.auth.dao.RoleDao;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.service.serviceImpl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description RoleServiceImpl:
 * @date 2019/3/7 17:02
 **/
@Service
public class RoleServiceImpl extends SuperServiceImpl<Long,RoleDao,Role> implements RoleService{
}
