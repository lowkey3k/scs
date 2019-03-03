package com.shanxiut.scs.param;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午4:05
 **/
@Data
public class CrudParam<T> {

    private String column;

    private Object value;

    private List<Term> terms = new ArrayList<>();

    public CrudParam empty() {
        return new CrudParam();
    }

    public CrudParam add(Term term) {
        this.terms.add(term);
        return this;
    }

    public CrudParam add(List<Term> terms) {
        this.terms.addAll(terms);
        return this;
    }

    public CrudParam and(String column, Object value) {
        return this.add(Term.and(column,value));
    }

    public CrudParam or(String column, Object value) {
        return this.or(column,TermEnum.eq,value);
    }

    public CrudParam and(String column,TermEnum termEnum,Object value){
        this.terms.add(Term.build(column,termEnum,value));
        return this;
    }

    public CrudParam or(String column,TermEnum termEnum,Object value){
        this.terms.add(Term.or(column,termEnum,value));
        return this;
    }

}
