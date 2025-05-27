package com.vueart.api.common.auth.handler;

import com.vueart.api.common.auth.dto.CustomOauth2UserDetails;
import com.vueart.api.config.security.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomOauth2UserDetails userDetails = (CustomOauth2UserDetails) authentication.getPrincipal();

        String token = jwtTokenProvider.generateAccessToken(String.format("%s", userDetails.getUserId()));

        String redirectUrl = "http://localhost:3000/oauth/redirect?token=" + token;
        response.sendRedirect(redirectUrl);
    }
}
