package com.vueart.api.common.auth.dto;

public interface OAuth2UserInfo {
    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
