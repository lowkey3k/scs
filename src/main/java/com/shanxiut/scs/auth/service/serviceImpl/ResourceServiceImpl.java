package com.shanxiut.scs.auth.service.serviceImpl;

import com.shanxiut.scs.auth.dao.ResourceDao;
import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.service.ResourceService;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.service.serviceImpl.SuperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description ResourceServiceImpl:
 * @date 2019/3/7 17:03
 **/
@Service
public class ResourceServiceImpl extends SuperServiceImpl<Long,ResourceDao,Resource> implements ResourceService {
    @Autowired
    private RoleService roleService;
    @Override
    public List<Resource> selectOwnerResourceByRoleId(Long id) {
        Role role = roleService.findById(id);

        List<Resource> resources = this.findAll();
        resources.stream().forEach(resource -> {
            if (getResourceName(role).contains(resource.getName())) {
                resource.setOwner(true);
            } else {
                resource.setOwner(false);
            }

        });
        return resources;

    }

    private List<String> getResourceName(Role role){
        return role.getResources().stream().map(Resource::getName).collect(Collectors.toList());
    }
}
