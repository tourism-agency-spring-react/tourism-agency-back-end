package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.payload.AuthResponse;
import com.tourismagency.tourism_agency.service.interfaces.IAuthService;
import com.tourismagency.tourism_agency.util.jwt.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = this.getAuthentication(loginRequestDTO.email(), loginRequestDTO.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtils.createToken(authentication);

        Set<String> authorities = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)  // Extrae el nombre de cada rol
                .collect(Collectors.toSet());

        return AuthResponse
                    .builder()
                    .token(token)
                    .message("Autenticacion exitosa.")
                    .email(authentication.getName())
                    .roles(authorities)
                    .build();
    }

    @Override
    public Authentication getAuthentication(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("El email o contraseña son incorrectos.");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword()) || !userDetails.getUsername().equals(email)) {
            throw new BadCredentialsException("El email o contraseña no son validos.");
        }

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }

}
