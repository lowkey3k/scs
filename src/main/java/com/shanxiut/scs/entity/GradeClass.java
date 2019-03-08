package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author LiHaitao
 * @description GradeClass:班级实体类
 * @date 2019/3/8 13:48
 **/
@Data
@Entity
@Table(name = "scs_grade_class")
public class GradeClass extends SuperEntity<GradeClass> {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String code;

    private String name;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示department不能为空。删除文章，不影响department
    @JoinColumn(name="department_id",referencedColumnName = "id")//设置在department表中的关联字段(外键)
    private Department department;

   /* @OneToMany(mappedBy = "gradeClass",cascade=CascadeType.ALL)
    private List<Student> students;
*/

}
