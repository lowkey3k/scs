package com.shanxiut.scs.auth.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.shanxiut.scs.auth.dao.RoleDao;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.auth.vo.RoleVO;
import com.shanxiut.scs.auth.service.UserService;
import com.shanxiut.scs.service.serviceImpl.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description RoleServiceImpl:
 * @date 2019/3/7 17:02
 **/
@Service
public class RoleServiceImpl extends SuperServiceImpl<Long, RoleDao, Role> implements RoleService {


    @Autowired
    private UserService userService;


    @Override
    public List<RoleVO> selectOwnerRoleByUserId(Long id) {
        User user = userService.findById(id);
        List<Role> roles = this.findAll();
        return roles.stream().map(role -> {
            RoleVO roleVO = entityToModel(role);
            if (getRoleName(user).contains(role.getRole())) {
                roleVO.setOwner(true);
            } else {
                roleVO.setOwner(false);
            }
            return roleVO;
        }).collect(Collectors.toList());
    }


    private RoleVO entityToModel(Role role) {
        RoleVO roleVO = new RoleVO();
        BeanUtil.copyProperties(role, roleVO);
        return roleVO;
    }

    private List<String> getRoleName(User user) {
        if (user.getRoles() != null) {
            return user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
