package com.shanxiut.scs.controller;

import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.service.ResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description ResourceController:
 * @date 2019/3/8 14:12
 **/
@RestController
@RequestMapping("/resource")
public class ResourceController extends AbstractCrudController<Resource,Long,ResourceService> {
}
