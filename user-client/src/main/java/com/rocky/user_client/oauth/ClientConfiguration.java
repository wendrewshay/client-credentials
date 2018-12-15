package com.rocky.user_client.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableOAuth2Client
public class ClientConfiguration {

    @Autowired
    private ClientTokenServices clientTokenServices;

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public OAuth2ProtectedResourceDetails passwordResourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setId("oauth2Server");
        details.setTokenName("oauth token");
        details.setClientId("clientapp");
        details.setClientSecret("123456");
        details.setAccessTokenUri("http://localhost:8080/oauth/token");
        details.setScope(Arrays.asList("read_profile"));
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        return details;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        OAuth2ProtectedResourceDetails resourceDetails = passwordResourceDetails();
        OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails, oAuth2ClientContext);

        AccessTokenProviderChain provider = new AccessTokenProviderChain(Collections.singletonList(new ClientCredentialsAccessTokenProvider()));
        provider.setClientTokenServices(clientTokenServices);
        template.setAccessTokenProvider(provider);
        return template;
    }
}
