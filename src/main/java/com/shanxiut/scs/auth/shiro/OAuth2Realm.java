package com.shanxiut.scs.auth.shiro;

import com.shanxiut.scs.auth.entity.Resource;
import com.shanxiut.scs.auth.entity.Role;
import com.shanxiut.scs.common.param.CrudParam;
import com.shanxiut.scs.common.param.Term;
import com.shanxiut.scs.auth.entity.User;
import com.shanxiut.scs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 认证
 *
 * @author LiHaitao
 * @description :
 * @date 2019/3/6 19:44
 **/

@Slf4j
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        User user = (User) principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<String>();
        CrudParam crudParam = new CrudParam();
        crudParam.add(Term.build("number", user.getNumber()));
        User u = userService.findAll(crudParam).get(0);
        Set<String> collectRoles = u.getRoles().stream().map(role -> {
            return role.getRole();
        }).collect(Collectors.toSet());
        //添加角色
        info.setRoles(collectRoles);
        //添加资源
        Set<String> resources = new HashSet<>();
        for (Role role : user.getRoles()) {
            for (Resource resource : role.getResources()) {
                resources.add(resource.getName());
            }
        }
        info.setStringPermissions(resources);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String accessToken = (String) authenticationToken.getPrincipal();
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        log.info("验证当前Subject时获取到token为：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        String number = (String) token.getPrincipal();

        CrudParam crudParam = new CrudParam();
        crudParam.add(Term.build("number", number));
        List<User> all = userService.findAll(crudParam);
        if (all.isEmpty()) {
            return null;
        }
        User user = all.get(0);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(user.getNumber()), getName());
        //将用户信息放入session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(user.getNumber(), user);
        session.setTimeout(30);
        return simpleAuthenticationInfo;

    }
}
