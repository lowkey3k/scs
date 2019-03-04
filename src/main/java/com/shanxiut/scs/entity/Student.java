package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Description:
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

    @Column(name = "student_name")
    private String studentName;

    private String sex;

    private String birthday;




}
