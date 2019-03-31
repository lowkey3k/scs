package com.shanxiut.scs.common.param;

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
public class CrudParam {

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
        this.and(column, TermEnum.eq,value);
        return this;
    }

    public CrudParam or(String column, Object value) {
        this.or(column, TermEnum.eq,value);
        return this;
    }

    public CrudParam and(String column, TermEnum termEnum, Object value){
        Term term=new Term();
        term.setColumn(column);
        term.setValue(value);
        term.setTermType(termEnum);
        term.setType(Term.Type.and);
        this.terms.add(term);
        return this;
    }

    public CrudParam or(String column, TermEnum termEnum, Object value){
        Term term=new Term();
        term.setColumn(column);
        term.setValue(value);
        term.setTermType(termEnum);
        term.setType(Term.Type.or);
        this.terms.add(term);
        return this;
    }

}
