package com.shanxiut.scs.common.param;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午4:07
 **/
@Data
public class Term implements Cloneable {

/*

    private String column;

    private Object value;

    private TermEnum termEnum;

    private TermType type;



    public static Term build(String column,Object value){
        Term term=new Term();
        term.setColumn(column);
        term.setValue(value);
        term.setTermEnum(TermEnum.eq);
        term.setType(TermType.and);
        return term;
    }
    public static Term build(String column,TermEnum termEnum,Object value){
        return and(column,termEnum,value);
    }
    public static Term or(String column,Object value){
        return or(column,TermEnum.eq,value);
    }

    public static Term or(String column,TermEnum termEnum,Object value){
        Term term=new Term();
        term.setColumn(column);
        term.setValue(value);
        term.setTermEnum(termEnum);
        term.setType(TermType.or);
        return term;
    }

    public static Term and(String column,Object value){
        return and(column,TermEnum.eq,value);
    }

    public static Term and(String column,TermEnum termEnum,Object value){
        Term term=new Term();
        term.setColumn(column);
        term.setValue(value);
        term.setTermEnum(termEnum);
        term.setType(TermType.and);
        return term;
    }


*/
private String column;
    private Object value;
    private Term.Type type;
    private TermEnum termType;

    private List<Term> terms;

    public Term() {
        this.type = Term.Type.and;
        this.termType = TermEnum.eq;
        this.terms = new LinkedList();
    }

    public Term or(String term, Object value) {
        return this.or(term, TermEnum.eq, value);
    }

    public Term and(String term, Object value) {
        return this.and(term, TermEnum.eq, value);
    }

    public Term or(String term, TermEnum termType, Object value) {
        Term queryTerm = new Term();
        queryTerm.setTermType(termType);
        queryTerm.setColumn(term);
        queryTerm.setValue(value);
        queryTerm.setType(Term.Type.or);
        this.terms.add(queryTerm);
        return this;
    }

    public Term or(Term orTerm) {
        orTerm.setType(Term.Type.or);
        this.terms.add(orTerm);
        return this;
    }

    public Term and(String term, TermEnum termType, Object value) {
        Term queryTerm = new Term();
        queryTerm.setTermType(termType);
        queryTerm.setColumn(term);
        queryTerm.setValue(value);
        queryTerm.setType(Term.Type.and);
        this.terms.add(queryTerm);
        return this;
    }

    public Term and(Term orTerm) {
        orTerm.setType(Term.Type.and);
        this.terms.add(orTerm);
        return this;
    }



    public String getColumn() {
        return this.column;
    }

    public Term setColumn(String column) {
            this.column = column;
            return this;
    }

    public Object getValue() {
        return this.value;
    }

    public Term setValue(Object value) {
        this.value = value;
        return this;
    }

    public Term.Type getType() {
        return this.type;
    }

    public Term setType(Term.Type type) {
        this.type = type;
        return this;
    }

    public TermEnum getTermType() {
        return this.termType;
    }

    public Term setTermType(TermEnum termType) {
        this.termType = termType;
        return this;
    }

    public List<Term> getTerms() {
        return this.terms;
    }

    public Term setTerms(List<Term> terms) {
        this.terms = terms;
        return this;
    }

    public Term addTerm(Term term) {
        this.terms.add(term);
        return this;
    }

    public Term clone() {
        Term term = new Term();
        term.setColumn(this.column);
        term.setValue(this.value);
        term.setTermType(this.termType);
        term.setType(this.type);
        this.terms.forEach((t) -> {
            term.addTerm(t.clone());
        });
        return term;
    }

    public static Term build(String column, Object value) {
        return build(column, TermEnum.eq, value);
    }

    public static Term build(String column, TermEnum operator) {
        return build(column, operator, (Object)null);
    }

    public static Term build(String column, TermEnum operator, Object value) {
        Term term = new Term();
        term.column = column;
        term.value = value;
        term.termType = operator;
        return term;
    }

    public static enum Type {
        or,
        and;

        private Type() {
        }

        public static Term.Type fromString(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception var2) {
                return and;
            }
        }
    }

}
