package com.shanxiut.scs.dao;

import com.shanxiut.scs.service.SuperService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;


/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午2:04
 **/
@NoRepositoryBean
public interface SuperDao<E,PK> extends JpaRepository<E,PK>,JpaSpecificationExecutor<E> {


}
