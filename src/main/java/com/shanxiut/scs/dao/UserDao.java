package com.shanxiut.scs.dao;

import com.shanxiut.scs.auth.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/6 下午8:19
 **/
@Repository
public interface UserDao extends SuperDao<User,Long>  {


}
