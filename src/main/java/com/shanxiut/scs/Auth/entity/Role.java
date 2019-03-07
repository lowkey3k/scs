package com.shanxiut.scs.Auth.entity;

import com.shanxiut.scs.entity.SuperEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author LiHaitao
 * @description Role:
 * @date 2019/3/7 16:50
 **/
@Data
@Entity
@Table(name = "scs_auth_role")
public class Role extends SuperEntity<Role> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String role;

    private String description;


    @ManyToMany
    @JoinTable(name = "scs_auth_role_resource",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})

    //1、关系维护端，负责多对多关系的绑定和解除
    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Authority)
    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为user_authority
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    private List<Resource> resources;


    private Boolean available;
}
