package com.shanxiut.scs.service;

import com.shanxiut.scs.entity.SuperEntity;
import com.shanxiut.scs.param.CrudParam;
import com.shanxiut.scs.param.Param;
import com.shanxiut.scs.param.Term;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午2:13
 **/
public interface SuperService<E,PK> {



    E findById(PK id);

    List<E>  findAll();

    List<E> findAll(CrudParam<? extends Term> param);


    Page<E> selectPage(Pageable pageable);

    void deleteById(PK id);

    void deleteByIds(Iterable<? extends E> var1);

    E updateById(E e);


    E insert(E e);

}
