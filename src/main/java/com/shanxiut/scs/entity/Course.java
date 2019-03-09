package com.shanxiut.scs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
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


    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers;

    @Column(name = "course_code")
    private String courseCode;//课程号

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

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Schedule> schedules;

    private String isSelection;//是否选修 0 是 1 否
}
