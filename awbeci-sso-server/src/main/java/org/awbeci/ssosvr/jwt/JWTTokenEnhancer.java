package org.awbeci.ssosvr.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

// jwt token 扩展器，加进自己的数据内容
public class JWTTokenEnhancer implements TokenEnhancer {

    // 要进行增强需要覆盖enhance方法
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("enhancer_content", "there is enhancer content");
        // 所有附加的属性都放到HashMap中，并设置在传入该方法的accessToken变量上
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}

