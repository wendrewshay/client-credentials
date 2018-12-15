package com.rocky.oauth.authorization_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     * @param security 安全对象
     * @author by Joney on 2018/12/13 22:22
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(passwordEncoder()).tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    /**
     * 配置客户端详情
     * @param clients 客户端详情
     * @author by Joney on 2018/12/13 22:18
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource);
    }

    /**
     * 配置授权(authorization)以及令牌(token)的访问端点和令牌服务(token services)
     * @param endpoints 访问端点
     * @author by Joney on 2018/12/13 22:19
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
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

    /**
     * 使用BCrypt强哈希方法来加密密码
     * @return PasswordEncoder
     * @author by Joney on 2018/12/13 22:21
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}
