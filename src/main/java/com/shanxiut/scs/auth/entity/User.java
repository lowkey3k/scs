package com.shanxiut.scs.auth.entity;

import com.shanxiut.scs.entity.SuperEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;
    @NotBlank
    private String number;//工号/学号

    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String code;

    private String salt;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "scs_auth_user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    private Set<Role> roles; //1、关系维护端，负责多对多关系的绑定和解除
    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(Authority)
    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为user_authority
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表


}
