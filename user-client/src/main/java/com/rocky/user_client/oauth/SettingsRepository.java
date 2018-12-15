package com.rocky.user_client.oauth;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

/**
 * 通过用户会话保持访问token的信息
 * @author by Joney on 2018/12/11 15:29
 */
@Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SettingsRepository {

    private String accessToken;

    private Calendar expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Calendar getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Calendar expiresIn) {
        this.expiresIn = expiresIn;
    }

}
