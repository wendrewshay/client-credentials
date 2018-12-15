//package com.rocky.oauth.resources_server.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//
///**
// * 调用远程Auth server进行token校验
// * @author by Joney on 2018/12/15 9:47
// */
//@Configuration
//@EnableResourceServer
//public class RemoteOAuth2ResourceServer extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId("oauth2-server");
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated()
//                .and()
//                .requestMatchers().antMatchers("/api/**")
//                .and()
//                .cors();
//    }
//
//    @Bean
//    public RemoteTokenServices remoteTokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//        remoteTokenServices.setClientId("clientapp");
//        remoteTokenServices.setClientSecret("123456");
//        return remoteTokenServices;
//    }
//}
