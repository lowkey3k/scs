package com.shanxiut.scs.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shanxiut.scs.entity.SuperEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author LiHaitao
 * @description Resource:
 * @date 2019/3/7 16:50
 **/
@Data
@Entity
@Table(name = "scs_auth_resource")
@JsonIgnoreProperties( value={"hibernateLazyInitializer","handler"})//jackson把懒加载也作为pojo进行序列化了
public class Resource extends SuperEntity<Resource> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String type;

    private String priority;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Resource parent;

    private String permission;

    /**
     * 是否可用
     */
    private boolean available;

}
