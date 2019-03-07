package com.shanxiut.scs.Auth.service.serviceImpl;

import com.shanxiut.scs.Auth.dao.RoleDao;
import com.shanxiut.scs.Auth.entity.Role;
import com.shanxiut.scs.Auth.service.RoleService;
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
