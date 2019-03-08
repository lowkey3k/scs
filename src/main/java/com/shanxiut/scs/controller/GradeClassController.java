package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.GradeClass;
import com.shanxiut.scs.service.GradeClassService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description GradeClassController:
 * @date 2019/3/8 14:01
 **/
@RestController
@RequestMapping("/grade_class")
public class GradeClassController extends AbstractCrudController<GradeClass,Long,GradeClassService> {

}
