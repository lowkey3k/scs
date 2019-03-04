package com.shanxiut.scs.service.serviceImpl;

import com.shanxiut.scs.dao.SuperDao;
import com.shanxiut.scs.entity.SuperEntity;
import com.shanxiut.scs.param.*;
import com.shanxiut.scs.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午2:13
 **/
public class SuperServiceImpl<PK extends Serializable, Dao extends SuperDao<E, PK>, E extends SuperEntity> implements SuperService<E, PK> {
    @Autowired
    private Dao superDao;


    @Override
    public E findById(PK id) {
        return superDao.findById(id).get();
    }

    @Override
    public List<E> findAll() {
        return superDao.findAll();
    }

    @Override
    public List<E> findAll(CrudParam<? extends Term> param) {
        List<E> resultList = null;
        Specification querySpecifi = new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                param.getTerms().forEach(term -> {
                    switch (term.getTermEnum()) {
                        case eq: {
                            if (term.getType().equals(TermType.and)) {
                                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(term.getColumn()), term.getValue())));

                            } else if (term.getType().equals(TermType.or)) {
                                predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get(term.getColumn()), term.getValue())));

                            }
                        }
                        break;
                        case lt: {
                            if (term.getType().equals(TermType.and)) {
                                predicates.add(criteriaBuilder.and(criteriaBuilder.lt(root.get(term.getColumn()), (Number) term.getValue())));

                            } else if (term.getType().equals(TermType.or)) {
                                predicates.add(criteriaBuilder.or(criteriaBuilder.lt(root.get(term.getColumn()), (Number) term.getValue())));

                            }
                        }
                        break;
                        case gt: {
                            if (term.getType().equals(TermType.and)) {
                                predicates.add(criteriaBuilder.and(criteriaBuilder.gt(root.get(term.getColumn()), (Number) term.getValue())));

                            } else if (term.getType().equals(TermType.or)) {
                                predicates.add(criteriaBuilder.or(criteriaBuilder.gt(root.get(term.getColumn()), (Number) term.getValue())));

                            }
                        }
                        break;
                        case lte: {
                            if (term.getType().equals(TermType.and)) {
                                predicates.add(criteriaBuilder.and(criteriaBuilder.le(root.get(term.getColumn()), (Number) term.getValue())));

                            } else if (term.getType().equals(TermType.or)) {
                                predicates.add(criteriaBuilder.or(criteriaBuilder.le(root.get(term.getColumn()), (Number) term.getValue())));

                            }
                        }
                        break;
                        case gte: {
                            if (term.getType().equals(TermType.and)) {
                                predicates.add(criteriaBuilder.and(criteriaBuilder.ge(root.get(term.getColumn()), (Number) term.getValue())));

                            } else if (term.getType().equals(TermType.or)) {
                                predicates.add(criteriaBuilder.or(criteriaBuilder.ge(root.get(term.getColumn()), (Number) term.getValue())));

                            }
                        }
                        break;
                        case like: {
                            if (term.getType().equals(TermType.and)) {
                                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get(term.getColumn()), "%" + term.getValue() + "%")));

                            } else if (term.getType().equals(TermType.or)) {
                                predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get(term.getColumn()), "%" + term.getValue() + "%")));

                            }
                        }
                        break;
                        default:
                            return;
                    }


                });


                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList = this.superDao.findAll(querySpecifi);
        return resultList;
    }

    @Override
    public Page<E> selectPage(Pageable pageable) {
        return superDao.findAll(pageable);
    }

    @Override
    public void deleteById(PK id) {
        superDao.deleteById(id);
    }

    @Override
    public void deleteByIds(Iterable<? extends E> var1) {
        superDao.deleteAll(var1);
    }

    @Override
    public E updateById(E e) {
        return superDao.save(e);
    }

    @Override
    public E insert(E e) {
        return superDao.save(e);
    }
}
