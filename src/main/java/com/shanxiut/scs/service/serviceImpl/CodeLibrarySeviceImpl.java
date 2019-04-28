package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.CodeLibraryDao;
import com.shanxiut.scs.entity.CodeLibrary;
import com.shanxiut.scs.service.CodeLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hliu on 2019/4/28.
 */
@Service
public class CodeLibrarySeviceImpl extends SuperServiceImpl<Long,CodeLibraryDao,CodeLibrary> implements CodeLibraryService {


    @Autowired
    private CodeLibraryDao codeLibraryDao;
    
    @Override
    public List<CodeLibrary> findByCodeNo(String codeNo) {
        return codeLibraryDao.findCodeLibraryByCodeNo(codeNo);
    }

}
