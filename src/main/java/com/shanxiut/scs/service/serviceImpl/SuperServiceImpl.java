package com.shanxiut.scs.service.serviceImpl;

import cn.hutool.core.collection.CollectionUtil;
import com.shanxiut.scs.dao.SuperDao;
import com.shanxiut.scs.entity.SuperEntity;
import com.shanxiut.scs.common.param.CrudParam;
import com.shanxiut.scs.common.param.Term;
import com.shanxiut.scs.common.param.TermEnum;
import com.shanxiut.scs.service.SuperService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

import static sun.corba.Bridge.get;

/**
 * Description:
 *
 * @Author lht
 * @Da te 2019/3/3 下午2:13
 **/
public class SuperServiceImpl<PK extends Serializable, Dao extends SuperDao<E, PK>, E extends SuperEntity> implements SuperService<E, PK> {

    @Autowired
    private Dao superDao;


    @Override
    public SuperDao<E, PK> getDao() {
        return superDao;
    }

    @Override
    public E findById(PK id) {
        return superDao.getOne(id);
    }

    @Override
    public List<E> findAll() {
        return superDao.findAll();
    }


    @Override
    public List<E> findAll(CrudParam param) {
        Specification specification = this.buildSpecification(param);
        return this.superDao.findAll(specification);
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
    public void deleteByIds(Iterable<E> var1) {
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

    @Override
    public E selectSingle(CrudParam param) {
        Specification<E> specification = this.buildSpecification(param);
        List<E> all = superDao.findAll(specification);
        if (CollectionUtil.isEmpty(all)){
            return null;
        }
        return all.get(0);
    }

    protected Specification<E> buildSpecification(CrudParam param) {
        Specification<E> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.addAll(this.buildSpecificationByPropertyFilter(param.getTerms(), root, cb));
            return cb.and((Predicate[]) predicateList.toArray(new Predicate[predicateList.size()]));
        };
        return specification;
    }

    protected List<Predicate> buildSpecificationByPropertyFilter(List<Term> terms, Root root, CriteriaBuilder cb) {
        List<Predicate> predicateList = new ArrayList<>();
        Iterator var5 = terms.iterator();

        while (var5.hasNext()) {
            Term term = (Term) var5.next();
            Predicate predicate = this.buildPredicate(term, root, cb);
            if (predicate != null) {
                predicateList.add(predicate);
            }
        }

        return predicateList;
    }

    protected Predicate buildPredicate(Term term, Root root, CriteriaBuilder cb) {

        boolean nullTerm = term.getColumn() == null && term.getColumn().equals("");
        if (nullTerm && term.getTerms().isEmpty()) {
            return null;
        } else {
            Predicate predicate = null;
            if (!nullTerm) {
                predicate = this.buildPredicate(root.get(term.getColumn()), term.getValue(), term.getTermType(), cb);
            }

            if (!term.getTerms().isEmpty()) {//改过
                Iterator var6 = term.getTerms().iterator();

                while (var6.hasNext()) {
                    Term innerTerm = (Term) var6.next();
                    Predicate tmp = this.buildPredicate(innerTerm, root, cb);
                    if (predicate == null) {
                        predicate = tmp;
                    } else {
                        switch (innerTerm.getType()) {
                            case and:
                                predicate = cb.and(predicate, tmp);
                                break;
                            case or:
                                predicate = cb.or(predicate, tmp);
                        }
                    }
                }
            }

            return predicate;
        }

    }

    protected Predicate buildPredicate(Path propertyPath, Object propertyValue, TermEnum termType, CriteriaBuilder cb) {
        Object value = propertyValue;
        Predicate predicate = null;
        switch (termType) {
            case eq:
                predicate = cb.equal(propertyPath, value);
                break;
            case like:
                String likeValue = String.valueOf(value);
                predicate = cb.like(propertyPath, StringUtils.contains(likeValue, "%") ? likeValue : "%" + likeValue + "%");
                break;
            case lte:
                predicate = cb.le(propertyPath, Long.parseLong(value + ""));
                break;
            case lt:
                predicate = cb.lt(propertyPath, Long.parseLong(value + ""));
                break;
            case gte:
                predicate = cb.ge(propertyPath, Long.parseLong(value + ""));
                break;
            case gt:
                predicate = cb.gt(propertyPath, Long.parseLong(value + ""));
                break;
            case notnull:
                predicate = cb.isNotNull(propertyPath);
                break;
            case isnull:
                predicate = cb.isNull(propertyPath);
                break;
            case not:
                predicate = cb.notEqual(propertyPath, value);
                break;
            case nlike:
                predicate = cb.notLike(propertyPath, value + "");
                break;
            case in:
                if (value != null && value instanceof Collection && ((Collection) value).size() > 0) {
                    predicate = propertyPath.in((Collection) value);
                }
                break;
            case nin:
                if (value != null && value instanceof Collection && ((Collection) value).size() > 0) {
                    predicate = cb.not(propertyPath.in((List) value));
                }
                break;
            case btw:
                predicate = cb.between(propertyPath, (Comparable) ((List) value).get(0), (Comparable) ((List) value).get(1));
                break;
            case nbtw:
                predicate = cb.not(cb.between(propertyPath, (Comparable) ((List) value).get(0), (Comparable) ((List) value).get(1)));
                break;
            case empty:
                predicate = cb.isEmpty(propertyPath);
                break;
            case nempty:
                predicate = cb.isNotEmpty(propertyPath);
                break;
            default:
        }

        return predicate;
    }

}

       /* List<E> resultList = null;
>>>>>>> 66a885115777b7b44e670b6ddc058e4627ac5156
        Specification querySpecifi = new Specification<E>() {
            @Override
            public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                List<Predicate> orP = new ArrayList<>();
                List<Predicate> andP = new ArrayList<>();
                Predicate predicate = null;
                for (Term term : param.getTerms()) {
                    switch (term.getTermEnum()) {
                        case eq: {
                            predicate = criteriaBuilder.equal(root.get(term.getColumn()), term.getValue());
                        }
                        break;
                        case lt: {
                            predicate = criteriaBuilder.lt(root.get(term.getColumn()), (Number) term.getValue());
                        }
                        break;
                        case gt: {
                            predicate = criteriaBuilder.gt(root.get(term.getColumn()), (Number) term.getValue());
                        }
                        break;
                        case lte: {
                            predicate = criteriaBuilder.le(root.get(term.getColumn()), (Number) term.getValue());
                        }
                        break;
                        case gte: {
                            predicate = criteriaBuilder.ge(root.get(term.getColumn()), (Number) term.getValue());
                        }
                        break;
                        case like: {
                            predicate = criteriaBuilder.like(root.get(term.getColumn()), "%" + term.getValue() + "%");
                        }
                        break;
                        default:
                            continue;
                    }

                    if (term.getType().equals(TermType.and)) {
                        predicate = criteriaBuilder.and(predicate);
                    }
                    if (term.getType().equals(TermType.or)) {
                        predicate = criteriaBuilder.or(predicate);
                    }
                    predicates.add(predicate);

                }
                return criteriaBuilder.and((Predicate[])predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    */
