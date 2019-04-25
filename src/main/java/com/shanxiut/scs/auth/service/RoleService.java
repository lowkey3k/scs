package com.shanxiut.scs.auth.service;

import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.vo.RoleVO;
import com.shanxiut.scs.service.SuperService;

import java.util.List;

/**
 * @author LiHaitao
 * @description RoleService:
 * @date 2019/3/7 17:00
 **/
public interface RoleService extends SuperService<Role,Long> {

    List<RoleVO>  selectOwnerRoleByUserId(Long id);
}
