package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hliu on 2019/3/5.
 * 课程实体类
 */
@Data
@Entity
@Table(name = "scs_course")
public class Course extends SuperEntity<Course>{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "COURSE_TEACHER",joinColumns = {
            @JoinColumn(name = "COURSE_ID",referencedColumnName = "ID")},inverseJoinColumns = {
            @JoinColumn(name = "TEACHER_ID",referencedColumnName = "ID")
    })
    private Set<Teacher> teachers;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_hour")
    private Double courseHour;//学时

    @Column(name = "course_score")
    private Double courseScore;//学分

    @Column(name="choose_number")
    private Integer chooseNumber;//选课人数

    @Column(name = "start_term")
    private String startTerm;//开课学期

    @Column
    private String location;//上课地点

    @Column(name = "course_time")
    private String courseTime;//上课时间
}
