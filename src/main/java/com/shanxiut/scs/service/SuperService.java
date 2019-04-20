package com.shanxiut.scs.service;

import com.shanxiut.scs.common.param.CrudParam;
import com.shanxiut.scs.common.param.Term;
import com.shanxiut.scs.dao.SuperDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午2:13
 **/
public interface SuperService<E,PK> {

    SuperDao<E, PK> getDao();


    E findById(PK id);

    List<E>  findAll();

    List<E> findAll(CrudParam param);


    Page<E> selectPage(Pageable pageable);

    void deleteById(PK id);

    void deleteByIds(Iterable<E> var1);

    E updateById(E e);


    E insert(E e);

    E selectSingle(CrudParam param);

}
