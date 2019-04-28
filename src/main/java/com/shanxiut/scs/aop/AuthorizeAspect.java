package com.shanxiut.scs.aop;

import com.shanxiut.scs.annotation.Authorize;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.auth.service.ResourceService;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.common.exception.RestException;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.common.util.DateUtils;
import com.shanxiut.scs.common.util.ShiroUtils;
import com.shanxiut.scs.entity.SuperEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

/**
 * @Description:
 * @Author: Lihaitao
 * @Date: 2019/3/7 16:04
 * @UpdateUser:
 * @UpdateRemark:
 */
@Component
@Aspect
@Slf4j
@SuppressWarnings("all")
public class AuthorizeAspect {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;


    @Pointcut("@annotation(com.shanxiut.scs.annotation.Authorize)")
    public void logRequest() {

    }

    @Around("logRequest()")
    public Object before(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Object[] args = point.getArgs();
        Object result =point.proceed(args);
        User user;
        try {
             user = (User) ShiroUtils.getSubject().getPrincipal();
            //执行方法
            if (user == null) {
                return ResponseMessage.error(401, "未登录");
            }
        }catch (Exception e){
            throw new RestException("登录过期");
        }

        /**
         * 类级别权限注解
         */
        Object target = point.getTarget();
        Class<?> aClass = target.getClass();
        Authorize annotation = aClass.getAnnotation(Authorize.class);
        String[] resources;
        Optional<Authorize> annotation1 = Optional.ofNullable(annotation);

        /**
         * 方法j级别权限控制
         */
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Authorize authorize = method.getAnnotation(Authorize.class);
        String[] value = authorize.resources();
        String name = method.getName();

        if (user!=null&&name.equals("insert")){
            for (Object param:args){
                if (param instanceof SuperEntity){
                    ((SuperEntity) param).setCreateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
                    ((SuperEntity) param).setCreatorCode(user.getNumber());
                    ((SuperEntity) param).setStatus(1);
                    ((SuperEntity) param).setUpdaterCode(user.getCode());
                    ((SuperEntity) param).setUpdateTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
                }
            }
        }
        if (annotation1.isPresent()) {
            resources = annotation.resources();
            if (ShiroUtils.getSubject().isPermittedAll(resources)) {
                if (ShiroUtils.getSubject().isPermittedAll(value)) {
                    return result;
                }
            }
        } else {
            if (ShiroUtils.getSubject().isPermittedAll(value)){
                return result;
            }
        }
        return ResponseMessage.error(authorize.message());
    }

}
