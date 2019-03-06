package com.shanxiut.scs.common.util;

import com.shanxiut.scs.common.param.CrudParam;
import com.shanxiut.scs.common.param.Term;
import com.shanxiut.scs.common.param.TermEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author LiHaitao
 * @description CrudParamUtil:
 * @date 2019/3/4 14:38
 **/
public class CrudParamUtil {


    public static void padding(CrudParam crudParam, HttpServletRequest request) {
//        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, Object> f = ServletUtil.getParametersStartingWith(request, "f_");
        List<Term> terms = new ArrayList<>();
        Iterator iterator = f.keySet().iterator();
        while (iterator.hasNext()) {
            String param = iterator.next()+"";
            if (param.contains("_")) {
                String[] strings = param.split("_");
                Term term = new Term();
                term.setColumn(strings[1]);
                term.setValue(f.get(param));
                term.setType(Term.Type.and);
                switch (strings[0]) {
                    case "eq": {
                        term.setTermType(TermEnum.eq);
                    }
                    break;
                    case "lt": {
                        term.setTermType(TermEnum.lt);
                    }
                    break;
                    case "gt": {
                        term.setTermType(TermEnum.gt);
                    }
                    break;
                    case "lte": {
                        term.setTermType(TermEnum.lte);
                    }
                    break;
                    case "gte": {
                        term.setTermType(TermEnum.gte);
                    }
                    break;
                    case "like": {
                        term.setTermType(TermEnum.like);
                    }
                    break;
                    default:
                        return;
                }

                terms.add(term);
            }
        }
        crudParam.add(terms);
    }


}
