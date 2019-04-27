package com.shanxiut.scs.auth.service.serviceImpl;

import com.shanxiut.scs.auth.dao.UserDao;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.auth.service.UserService;
import com.shanxiut.scs.service.serviceImpl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/6 下午8:20
 **/
@Service
public class UserServiceImpl extends SuperServiceImpl<Long, UserDao, User> implements UserService {
}
