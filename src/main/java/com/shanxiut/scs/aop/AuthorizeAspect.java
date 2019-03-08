package com.shanxiut.scs.aop;

import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.auth.service.ResourceService;
import com.shanxiut.scs.auth.service.RoleService;
import com.shanxiut.scs.annotation.Authorize;
import com.shanxiut.scs.common.util.ShiroUtils;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.common.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
//        Class<? extends ProceedingJoinPoint> aClass = point.getClass();
        Authorize authorize = method.getAnnotation(Authorize.class);
        //执行方法
        Object result = point.proceed();
        String[] value = authorize.resources();
        List<String> list = Arrays.asList(value);//改请求方法的权限
        Session session = ShiroUtils.getSession();
        User user = (User) ShiroUtils.getSubject().getPrincipal();
        if (user == null) {
            return ResponseMessage.error(401, "未登录");
        }
        //如果在用户的所有权限中存在则通过权限
        for (Role role : user.getRoles()) {
            for (Resource resource : role.getResources()) {

                Optional<Resource> parent = Optional.ofNullable(resource.getParent());

                if (list.contains(resource.getName())) {
                    {
                        Optional<Resource> resource1 = Optional.ofNullable(resource.getParent());
//                        if (resource1.isPresent() && list.contains(resource1.get().getName())) {
                            return result;
//                        }

                    }
                }
            }
        }
        return ResponseMessage.error(authorize.message());
    }


}
