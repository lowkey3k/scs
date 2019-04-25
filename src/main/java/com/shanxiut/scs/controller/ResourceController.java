package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.service.ResourceService;
import com.shanxiut.scs.auth.vo.RoleVO;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.common.util.UpdateTool;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LiHaitao
 * @description ResourceController:
 * @date 2019/3/8 14:12
 **/
@RestController
@RequestMapping("/resource")
public class ResourceController extends AbstractCrudController<Resource,Long,ResourceService> {


    @GetMapping("/ownerAndAllResource/{id}")
    @AccessLogger("List结果查询")
//    @Authorize(resources = AuthConstant.Resource.QUERY)
    public ResponseMessage<List<Resource>> select(@PathVariable Long id) {
        return ResponseMessage.ok(service.selectOwnerResourceByRoleId(id));
    }


    @PutMapping("/update")
    @AccessLogger("更新资源信息")
    public ResponseMessage update(@RequestBody Resource resource){
        Resource re = service.findById(resource.getId());
        UpdateTool.copyNullProperties(re,resource);
        return ResponseMessage.ok(service.updateById(resource));

    }

}
