package org.awbeci.ssosvr.config;

import org.awbeci.ssosvr.jwt.JWTTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JWTTokenStoreConfig {

    // 设置TokenStore为JwtTokenStore
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    // 在jwt和oauth2服务器之间充当翻译
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 定义将用于签署令牌的签名密钥(自定义 存储在git上authentication.yml文件)
        // jwt是不保密的，所以要另外加签名验证jwt token
        // todo:最好不要写死，复杂点更好
        converter.setSigningKey("awbeci");
        return converter;
    }

    // 设置TokenEnhancer增强器中使用JWTTokenEnhancer增强器
    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new JWTTokenEnhancer();
    }

    // @Primary作用：如果有多个特定类型bean那么就使用被@Primary标注的bean类型进行自动注入
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        // 用于从出示给服务的令牌中读取数据
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }
}
