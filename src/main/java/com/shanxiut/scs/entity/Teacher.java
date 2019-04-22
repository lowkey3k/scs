package com.shanxiut.scs.entity;

import com.shanxiut.scs.auth.entity.User;
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
public class Teacher extends SuperEntity<Teacher>{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

   /* @ManyToMany(mappedBy = "teachers")
    private Set<Course> courses;
*/

    private String phoneNumber;//手机号

    private String sex;//性别

    private String email;//邮箱

    private String age;

    private String birthday;

    private String idNumber;//身份证号

    @OneToOne(cascade=CascadeType.ALL)//
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "teacher")
    //1、关系维护端，负责多对多关系的绑定和解除
    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Authority)
    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为user_authority
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    private Set<Schedule> schedules;

    @ManyToOne(optional = true)
    @JoinColumn(name = "department")
    private Department department;
}
