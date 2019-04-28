package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.entity.CodeLibrary;
import com.shanxiut.scs.entity.GradeClass;
import com.shanxiut.scs.service.CodeLibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2019/4/28.
 */

@RestController
@RequestMapping("/code_library")
public class CodeLibraryController extends AbstractCrudController<CodeLibrary,Long,CodeLibraryService>{
    @GetMapping("/getbycodelibrary/{codeno}")
    @AccessLogger("根据编码查找编码分项")
    public ResponseMessage<List<CodeLibrary>> selectbyCodeNo(@PathVariable("codeno")String codeNo){
        return ResponseMessage.ok(getService().findByCodeNo(codeNo));
    }
}

