package com.vueart.api.service.auth;

import com.vueart.api.dto.request.user.SignInRequest;
import com.vueart.api.dto.request.user.SignUpRequest;
import jakarta.validation.Valid;

public interface AuthService {
    void signUp(@Valid SignUpRequest req);

    String signIn(@Valid SignInRequest req);
}
