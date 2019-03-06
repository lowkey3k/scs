package com.shanxiut.scs.common.exception;

import com.shanxiut.scs.response.ResponseMessage;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 *
 * @author lihaitao
 */
@RestControllerAdvice
public class RestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RestException.class)
    public ResponseMessage handleRRException(RestException e) {
        ResponseMessage r = new ResponseMessage();

        return r.error("请求错误");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseMessage handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseMessage.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseMessage handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResponseMessage.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseMessage handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return ResponseMessage.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public ResponseMessage handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseMessage.error("");
    }
}
