package com.shanxiut.scs.Auth.entity;

import com.shanxiut.scs.entity.SuperEntity;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

import javax.persistence.*;

/**
 * @author LiHaitao
 * @description Resource:
 * @date 2019/3/7 16:50
 **/
@Data
@Entity
@Table(name = "scs_auth_resource")
public class Resource extends SuperEntity<Resource> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String type;

    private String priority;

    @Column(name = "parent_id")
    private Resource parent;

    private String permission;

    /**
     * 是否可用
     */
    private boolean available;

}
