package com.shanxiut.scs.dao;

import com.shanxiut.scs.entity.CodeLibrary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hliu on 2019/4/28.
 */
@Repository
public interface CodeLibraryDao extends SuperDao<CodeLibrary,Long>{

   public  List<CodeLibrary> findCodeLibraryByCodeNo(String CodeNo);
}
