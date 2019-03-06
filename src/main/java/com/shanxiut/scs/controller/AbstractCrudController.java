package com.shanxiut.scs.controller;

import com.shanxiut.scs.entity.SuperEntity;
import com.shanxiut.scs.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiHaitao
 * @description AbstractCrudController:
 * @date 2019/3/6 15:09
 **/
public abstract class AbstractCrudController<E extends SuperEntity, PK, S extends SuperService<E, PK>> extends SuperController<E,PK> {
    @Autowired
    protected S service;

    public AbstractCrudController() {
    }

    public S getService() {
        return this.service;
    }
}
