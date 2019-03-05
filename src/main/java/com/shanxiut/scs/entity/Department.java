package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/5 下午10:40
 **/
@Data
@Entity
public class Department extends SuperEntity<Department> {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
}
