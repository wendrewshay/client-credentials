package com.rocky.oauth.resources_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 采用共享数据库进行token校验
 * @author by Joney on 2018/12/15 9:47
 */
@Configuration
@EnableResourceServer
public class JdbcOAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Value("${oauth2.resource.id}")
    private String resourceId;

    @Autowired
    private DataSource dataSource;

    /**
     * 资源安全配置
     * @param resources 资源配置
     * @author by Joney on 2018/12/14 11:47
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore()).resourceId(resourceId);
    }

    /**
     * http安全配置
     * @param http http安全对象
     * @author by Joney on 2018/12/14 11:48
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .cors();
    }

    /**
     * 令牌存储配置
     * @return TokenStore
     * @author by Joney on 2018/12/13 22:21
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}
