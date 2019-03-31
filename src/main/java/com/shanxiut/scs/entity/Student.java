package com.shanxiut.scs.entity;

import com.shanxiut.scs.auth.entity.User;
import lombok.Data;

import javax.persistence.*;

/**
 * Description:学生实体类
 *
 * @Author lht
 * @Date 2019/3/3 下午2:02
 **/
@Data
@Entity
@Table(name = "scs_student")
public class Student extends SuperEntity<Student> {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    public Long id;

    private String sex;

    private String birthday;

    private Integer age;

    private String number;

    private String phone;

    private String email;

    private String idNumber;//身份证号码

    @ManyToOne(optional=false)
    @JoinColumn(name="department")
    private Department department;

    @OneToOne(cascade=CascadeType.ALL)//Student是关系的维护端，当删除 Student，会级联删除 User
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @ManyToOne(optional=false)
    @JoinColumn(name="grade_class")
    private GradeClass gradeClass;


}
