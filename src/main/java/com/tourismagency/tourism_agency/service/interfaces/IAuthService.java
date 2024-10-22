package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.payload.AuthResponse;
import org.springframework.security.core.Authentication;

public interface IAuthService {

    AuthResponse login(LoginRequestDTO loginRequestDTO);

    Authentication getAuthentication(String email, String password);
}
