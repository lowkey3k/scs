package com.shanxiut.scs.common.util;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import com.shanxiut.scs.common.exception.RestException;
import com.shanxiut.scs.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static User getUser() {
        User userEntity = (User) SecurityUtils.getSubject().getPrincipal();
        if (userEntity == null) {
            throw new RestException("您当前没有权限", 401);
        }
        return userEntity;
    }

    public static Long getUserId() {
        return getUser().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if (kaptcha == null) {
            throw new RestException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

    private static final Logger logger = LoggerFactory.getLogger(ShiroUtils.class);


    /**
     * 是否是Ajax请求
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (remoteAddr.equals("")||null==remoteAddr) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }

    /**
     * response 输出JSON
     * @param response
     * @param resultMap
     * @throws IOException
     */
    public static void writeJson(ServletResponse response, Map<String, Object> resultMap){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.print(JSONUtil.parseFromMap(resultMap));
        } catch (Exception e) {
            logger.error("输出JSON异常:", e);
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }

}
