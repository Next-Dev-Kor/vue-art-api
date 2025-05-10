package com.vueart.api.common.auth.dto;

import com.vueart.api.core.enums.Code;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class GoogleUserDetails implements OAuth2UserInfo{
    private Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return Code.SocialLoginType.GOOGLE.toString();
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
