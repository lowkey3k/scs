package com.shanxiut.scs.auth.vo;

import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.vo.SuperVO;
import lombok.Data;

import javax.persistence.criteria.Predicate;
import java.util.Set;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/4/25 下午3:18
 **/
@Data
public class RoleVO extends SuperVO<Role> {


    private String role;

    private String description;


    private Set<Resource> resources;


    private Boolean available;

    private Boolean owner;



}
