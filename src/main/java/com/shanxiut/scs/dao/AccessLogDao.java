package com.shanxiut.scs.dao;

import com.shanxiut.scs.entity.AccessLog;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description AccessLogDao:
 * @date 2019/3/6 16:07
 **/
@Repository
public interface AccessLogDao extends SuperDao<AccessLog,Long> {
}
