package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.AccessLogDao;
import com.shanxiut.scs.entity.AccessLog;
import com.shanxiut.scs.service.AccessLogService;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description AccessLogServiceImpl:
 * @date 2019/3/6 16:08
 **/
@Service()
public class AccessLogServiceImpl extends SuperServiceImpl<Long,AccessLogDao,AccessLog> implements AccessLogService {
}
