package com.shanxiut.scs.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Description:学院实体类
 *
 * @Author lht
 * @Date 2019/3/5 下午10:40
 **/
@Data
@Entity
@Table(name = "scs_department")
@DynamicUpdate
@DynamicInsert
public class Department extends SuperEntity<Department> {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String code;

    /*@OneToMany(mappedBy = "department",cascade=CascadeType.ALL)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    private List<GradeClass> gradeClasses;
*/
   /* @OneToMany(mappedBy = "department",cascade=CascadeType.ALL)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    private List<Student> students;
*/
//   @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
//   @JsonIgnore
//   private List<Teacher> teachers;
}
