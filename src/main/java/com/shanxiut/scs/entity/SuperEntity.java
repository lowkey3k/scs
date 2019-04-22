package com.shanxiut.scs.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午1:56
 **/
@Data
@DynamicUpdate
@DynamicInsert
public class SuperEntity<E extends SuperEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Column(name = "status")
    private Integer status;

    @Column(updatable = false, name = "creator_code")
    private String creatorCode;

    @Column(updatable = false, name = "create_time")
    private Long createTime;

    @Column(name = "updater_code")
    private String updaterCode;

    @Column(name = "update_time")
    private Long updateTime;



    public SuperEntity() {
    }


}
