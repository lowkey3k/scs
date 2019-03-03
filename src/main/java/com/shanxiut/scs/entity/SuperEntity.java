package com.shanxiut.scs.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午1:56
 **/
@Data
public class SuperEntity<E extends SuperEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
}
