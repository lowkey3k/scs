package com.shanxiut.scs.param;

import lombok.Data;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午4:07
 **/
@Data
public class Term implements Cloneable {


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



}
