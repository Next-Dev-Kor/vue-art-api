package com.vueart.api.config.security.exception;

import com.vueart.api.core.enums.Code;
import com.vueart.api.core.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Object exception = request.getAttribute("exception");
        if (ObjectUtils.isEmpty(exception)) { // Header에 JWT Token이 없을 경우 setting
            response.getWriter().write(mapper.writeValueAsString(new ErrorResponse(Code.ErrorCode.UNAUTHORIZED)));
        } else {
            response.getWriter().write(mapper.writeValueAsString(exception));
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
