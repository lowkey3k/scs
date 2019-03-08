package com.shanxiut.scs.auth.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
* @Description:    密码重试次数管理
* @Author:         LiHaitao
* @CreateDate:     2018/8/25 21:07
* @UpdateUser:
* @UpdateDate:
* @UpdateRemark:
* @Version:        1.0.0
*/
public class PasswordRetryLimit extends HashedCredentialsMatcher {
	private Cache<String, AtomicInteger> passwordRetryCache;

	public PasswordRetryLimit(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// 重试+1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// 清除重试次数
			passwordRetryCache.remove(username);
		}
		if (retryCount.incrementAndGet() > 5) {
			//重试次数大于5
			throw new ExcessiveAttemptsException();
		}
		return matches;
	}
}
