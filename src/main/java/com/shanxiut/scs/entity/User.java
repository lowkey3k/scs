package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/6 下午7:51
 **/
@Data
@Entity
@Table(name = "scs_user")
public class User extends SuperEntity<User> {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public Long id;

    private String name;


    private String password;


    private String code;

    private String salt;



}
