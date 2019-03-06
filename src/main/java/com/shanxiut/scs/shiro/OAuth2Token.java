package com.shanxiut.scs.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 * @author LiHaitao
 * @description :
 * @date 2019/3/6 19:44
 */

public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
