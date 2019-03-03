package com.shanxiut.scs.controller;

import com.shanxiut.scs.dao.SuperDao;
import com.shanxiut.scs.entity.SuperEntity;
import com.shanxiut.scs.param.CrudParam;
import com.shanxiut.scs.param.Term;
import com.shanxiut.scs.response.ResponseMessage;
import com.shanxiut.scs.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
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
public class SuperController<E extends SuperEntity,S extends SuperService> {
    @Autowired
    protected S service;

    @GetMapping
    public List<E> getAll(CrudParam<Term> crudParam, HttpServletRequest request){
        return service.findAll(crudParam);
    }

    @PostMapping
    public ResponseMessage<?> insert(@RequestBody E e){
        return ResponseMessage.ok(service.insert(e));
    }


    @DeleteMapping
    public ResponseMessage delete(Serializable id){
        service.deleteById(id);
        return ResponseMessage.ok();
    }

    @PutMapping
    public ResponseMessage<?> update(E e){
        return ResponseMessage.ok(service.updateById(e));
    }
}
