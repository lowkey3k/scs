package com.shanxiut.scs.aop;

import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.service.ResourceService;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.annotation.Authorize;
import com.shanxiut.scs.common.util.ShiroUtils;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.common.response.ResponseMessage;
import com.shanxiut.scs.entity.Student;
import com.shanxiut.scs.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.reflect.generics.tree.ClassSignature;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
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


        Object result = point.proceed();
        User user = (User) ShiroUtils.getSubject().getPrincipal();
        //执行方法
        if (user == null) {
            return ResponseMessage.error(401, "未登录");
        }
        /**
         * 类级别权限注解
         */
        Object target = point.getTarget();
        Class<?> aClass = target.getClass();
        Authorize annotation = aClass.getAnnotation(Authorize.class);
        String[] resources = annotation.resources();
        /**
         * 方法界别权限控制
         */
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Authorize authorize = method.getAnnotation(Authorize.class);
        String[] value = authorize.resources();

        if (isAllowed(user, Arrays.asList(resources))) {
            if (isAllowed(user, Arrays.asList(value))) {
                return result;
            }
        }
        return ResponseMessage.error(authorize.message());
    }

    public boolean isAllowed(User user, List<String> list) {
        //如果在用户的所有权限中存在则通过权限
        for (Role role : user.getRoles()) {
            for (Resource resource : role.getResources()) {
                if (list.contains(resource.getName())) {
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
