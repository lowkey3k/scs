package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.UserDao;
import com.shanxiut.scs.entity.User;
import com.shanxiut.scs.service.UserService;
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
