package com.shanxiut.scs.controller;

import com.shanxiut.scs.annotation.AccessLogger;
import com.shanxiut.scs.annotation.Authorize;
import com.shanxiut.scs.auth.constant.AuthConstant;
import com.shanxiut.scs.common.param.CrudParam;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.common.util.CrudParamUtil;
import com.shanxiut.scs.common.util.UpdateTool;
import com.shanxiut.scs.entity.SuperEntity;
import com.shanxiut.scs.service.SuperService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
public abstract class SuperController<E extends SuperEntity, PK> {

    public abstract SuperService<E, PK> getService();


    @GetMapping
    @AccessLogger("List结果查询")
//    @Authorize(resources = AuthConstant.Resource.QUERY)
    public ResponseMessage<List<E>> getAll(CrudParam crudParam, HttpServletRequest request) {
        CrudParamUtil.padding(crudParam, request);
        return ResponseMessage.ok(this.getService().findAll(crudParam));
    }

    @PostMapping
    @AccessLogger("插入")
//    @Authorize(resources = AuthConstant.Resource.INSERT)
    public ResponseMessage<E> insert(@RequestBody E e) {
        return ResponseMessage.ok(this.getService().insert(e));
    }


    @DeleteMapping("/{id}")
    @AccessLogger("根据id删除")
//    @Authorize(resources = AuthConstant.Resource.DELETE)
    public ResponseMessage delete(@PathVariable PK id) {
        getService().deleteById(id);
        return ResponseMessage.ok();
    }

    @PutMapping
    @AccessLogger("通过id更新")
//    @Authorize(resources = AuthConstant.Resource.UPDATE)
    public ResponseMessage<?> update(@RequestBody E e) {
        E byId = getService().findById((PK) e.getId());
        UpdateTool.copyNullProperties(byId,e);
        return ResponseMessage.ok(getService().updateById(e));
    }

    @GetMapping("/{id}")
    @AccessLogger("通过id查询")
    public ResponseMessage<E> getByPrimaryKey(@PathVariable("id") PK id){
        return ResponseMessage.ok(getService().findById(id));
    }

    @DeleteMapping
    @AccessLogger("通过id批量删除")
    public ResponseMessage batchDeleteByIds(@RequestBody List<E> ids){
           getService().deleteByIds(ids);
        return ResponseMessage.ok();
    }

    @GetMapping("/single")
    @AccessLogger("查询一个结果")
    public ResponseMessage<E> selectSingle(CrudParam crudParam, HttpServletRequest request){
        CrudParamUtil.padding(crudParam,request);
        return ResponseMessage.ok(getService().selectSingle(crudParam));
    }
}
