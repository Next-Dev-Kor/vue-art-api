package com.vueart.api.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vueart.api.core.enums.Code.ErrorCode;
import com.vueart.api.core.exception.ErrorResponse;
import com.vueart.api.entity.User;
import com.vueart.api.repository.user.UserRepository;
import com.vueart.api.service.user.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider jwtProvider;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader("Authorization");

        String username = null;

        // Bearer token 검증 후 user name 조회
        if(token != null && !token.isEmpty()) {
            String jwtToken = token.substring(7);

            username = jwtProvider.getUsernameFromToken(jwtToken);
        }

        // token 검증 완료 후 SecurityContextHolder 내 인증 정보가 없는 경우 저장
        if(username != null && !username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Spring Security Context Holder 인증 정보 set
            SecurityContextHolder.getContext().setAuthentication(getUserAuth(username));
        }

        filterChain.doFilter(request,response);
    }

    /**
     * token의 사용자 idx를 이용하여 사용자 정보 조회하고, UsernamePasswordAuthenticationToken 생성
     *
     * @param username 사용자 idx
     * @return 사용자 UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getUserAuth(String username) {
        Optional<User> userInfo = userRepository.findById(Long.parseLong(username));

        return new UsernamePasswordAuthenticationToken(userInfo.get().getId(),
                userInfo.get().getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(userInfo.get().getRole().name()))
        );
    }
}
