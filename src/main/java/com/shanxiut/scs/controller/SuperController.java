package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.SuperEntity;
import com.shanxiut.scs.param.CrudParam;
import com.shanxiut.scs.param.Term;
import com.shanxiut.scs.param.TermEnum;
import com.shanxiut.scs.response.ResponseMessage;
import com.shanxiut.scs.service.SuperService;
import com.shanxiut.scs.util.CrudParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午9:08
 **/
@RestController
@RequestMapping
@SuppressWarnings("all")
public abstract class SuperController<E,PK,S extends SuperService > {

    abstract <S extends SuperService<E,PK>> S getService();


    @GetMapping
    public List<E> getAll(CrudParam<Term> crudParam, HttpServletRequest request){
        CrudParamUtil.padding(crudParam,request);
        crudParam.add(Term.build("studentName", TermEnum.like,"刘"));
        return this.getService().findAll(crudParam);
    }

    @PostMapping
    public ResponseMessage<?> insert(@RequestBody E e){
        return ResponseMessage.ok(this.getService().insert(e));
    }


    @DeleteMapping
    public   ResponseMessage delete(PK id){
        getService().deleteById(id);
        return ResponseMessage.ok();
    }

    @PutMapping
    public ResponseMessage<?> update(E e){
        return ResponseMessage.ok(getService().updateById(e));
    }
}
