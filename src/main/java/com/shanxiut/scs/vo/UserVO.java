package com.shanxiut.scs.vo;


import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.entity.User;
import lombok.Data;

import java.util.Set;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/4/25 下午1:28
 **/
@Data
public class UserVO extends SuperVO<User> {

    public Long id;


    private String number;//工号/学号


    private String username;


    private String password;


    private String salt;


    private String roleStr;

    private Set<Role> roles;


}
