package com.shanxiut.scs.common.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @Author lht
 * @Date 2019/3/3 下午4:07
 **/
public enum  TermEnum {
    eq("eq"),
    not("not"),
    like("like"),
    nlike("nlike"),
    gt("gt"),
    lt("lt"),
    gte("gte"),
    lte("lte"),
    in("in"),
    nin("nin"),
    empty("empty"),
    nempty("nempty"),
    isnull("isnull"),
    notnull("notnull"),
    btw("btw"),
    nbtw("nbtw"),
    intersects("intersects"),
    disjoint("disjoint"),
    within("within"),
    contains("contains");

    private static final Map<String, TermEnum> CACHE = new HashMap();
    private String value;

    private TermEnum(String value) {
        this.value = value;
    }

    public static TermEnum parse(String operation) {
        return (TermEnum)CACHE.get(operation);
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }

    static {
        if (values() != null) {
            TermEnum[] var0 = values();
            int var1 = var0.length;

            for(int var2 = 0; var2 < var1; ++var2) {
                TermEnum operator = var0[var2];
                CACHE.put(operator.getValue(), operator);
            }
        }

    }


}
