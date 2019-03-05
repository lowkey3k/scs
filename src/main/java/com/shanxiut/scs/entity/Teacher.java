package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hliu on 2019/3/5.
 * 教师实体类
 */
@Data
@Entity
@Table(name = "scs_teacher")
public class Teacher {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToMany(mappedBy = "teachers")
    private Set<Course> courses;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_account")
    private String teacherAccount;//账号

    @Column(name = "teacher_password")
    private String teacherPassword;//密码

    private String phoneNumber;//手机号

    private String email;//邮箱

    private String age;

    private String birthday;
}
