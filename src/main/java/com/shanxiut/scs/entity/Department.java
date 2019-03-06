package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/5 下午10:40
 **/
@Data
@Entity
@Table(name = "scs_department")
public class Department extends SuperEntity<Department> {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String code;



}
