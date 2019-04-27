package com.shanxiut.scs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午1:56
 **/
@Data
@MappedSuperclass
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})//jackson把懒加载也作为pojo进行序列化了
public class SuperEntity<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "creator_code")
    private String creatorCode;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "updater_code")
    private String updaterCode;

    @Column(name = "update_time")
    private String updateTime;



    public SuperEntity() {
    }


}
