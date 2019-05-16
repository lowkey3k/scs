package com.shanxiut.scs.common.validator;

import cn.hutool.core.collection.CollectionUtil;
import com.shanxiut.scs.common.exception.RestException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RestException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RestException(message);
        }
    }
    public static void isEmpty(Iterable iterable, String message) {
        if (CollectionUtil.isEmpty(iterable)) {
            throw new RestException(message);
        }
    }
}
