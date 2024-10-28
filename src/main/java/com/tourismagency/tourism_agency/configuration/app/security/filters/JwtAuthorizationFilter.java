package com.tourismagency.tourism_agency.configuration.app.security.filters;

import com.tourismagency.tourism_agency.util.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //validamos primero que la peticion venga el header 'Authorization'
        String tokenFromRequest = request.getHeader("Authorization");

        if(tokenFromRequest != null && tokenFromRequest.startsWith("Bearer ")) {
            //si es valido le quitamos el prefijo Bearer
            String token = tokenFromRequest.substring(7);

            //validamos el token
            if(jwtUtils.isValidToken(token)) {

                //obtenemos el email y autoridades del token
                String email = jwtUtils.getEmailFromToken(token);
                List<? extends GrantedAuthority> authorities = new ArrayList<>(userDetailsService.loadUserByUsername(email).getAuthorities());

                //creamos un objeto Authentication que contendra los datos del usuario a autorizar
                UsernamePasswordAuthenticationToken userAuthenticated = new UsernamePasswordAuthenticationToken(email, null, authorities);

                //pasamos el objeto autenticado al contexto de seguridad y seteamos la autenticacion
                SecurityContextHolder.getContext().setAuthentication(userAuthenticated);
            }
        }

        //seguimos con el filtro sin importar si el token es valido o no
        filterChain.doFilter(request, response);
    }
}
