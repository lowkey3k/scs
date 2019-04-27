package com.shanxiut.scs.auth.dao;

import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.dao.SuperDao;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/6 下午8:19
 **/
@Repository
public interface UserDao extends SuperDao<User,Long> {


}
