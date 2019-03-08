package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.AccessLog;
import com.shanxiut.scs.service.AccessLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description AccessLoggerController:
 * @date 2019/3/8 14:05
 **/
@RestController
@RequestMapping("/access_log")
public class AccessLogController extends AbstractCrudController<AccessLog,Long,AccessLogService> {

}
