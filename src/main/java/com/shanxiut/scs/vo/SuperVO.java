package com.shanxiut.scs.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/4/25 下午1:42
 **/
@Data
public class SuperVO<E> implements Serializable {



    private Long id;


    private Integer status;

    private String creatorCode;

    private Long createTime;

    private String updaterCode;

    private Long updateTime;
}
