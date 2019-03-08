package com.shanxiut.scs.auth.service.serviceImpl;

import com.shanxiut.scs.auth.dao.ResourceDao;
import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.service.ResourceService;
import com.shanxiut.scs.service.serviceImpl.SuperServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description ResourceServiceImpl:
 * @date 2019/3/7 17:03
 **/
@Service
public class ResourceServiceImpl extends SuperServiceImpl<Long,ResourceDao,Resource> implements ResourceService {
}
