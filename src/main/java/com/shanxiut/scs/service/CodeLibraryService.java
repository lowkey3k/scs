package com.shanxiut.scs.service;

import com.shanxiut.scs.entity.CodeLibrary;

import java.util.List;

/**
 * Created by Administrator on 2019/4/28.
 */
public interface CodeLibraryService extends SuperService<CodeLibrary,Long> {
    List<CodeLibrary> findByCodeNo(String codeNo);
}
