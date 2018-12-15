package com.rocky.user_client.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class OAuth2ClientTokenServices implements ClientTokenServices {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, Authentication authentication) {
        String accessToken = settingsRepository.getAccessToken();
        Calendar expirationDate = settingsRepository.getExpiresIn();

        if (accessToken == null) return null;
        DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        oAuth2AccessToken.setExpiration(expirationDate.getTime());
        return oAuth2AccessToken;
    }

    @Override
    public void saveAccessToken(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, Authentication authentication, OAuth2AccessToken oAuth2AccessToken) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.setTime(oAuth2AccessToken.getExpiration());

        settingsRepository.setAccessToken(oAuth2AccessToken.getValue());
        settingsRepository.setExpiresIn(expirationDate);
    }

    @Override
    public void removeAccessToken(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, Authentication authentication) {
        settingsRepository.setAccessToken(null);
        settingsRepository.setExpiresIn(null);
    }
}
