package com.khoai.backend.security;

import com.khoai.backend.model.LoginSession;
import com.khoai.backend.repository.LoginSessionRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AuthFilter extends GenericFilterBean {
    private final LoginSessionRepository loginSessionRepository;

    public AuthFilter(LoginSessionRepository loginSessionRepository) {
        this.loginSessionRepository = loginSessionRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("GỌI ĐÒ ƠI");

        HttpServletRequest request = (HttpServletRequest)servletRequest;

        String token = request.getHeader("Authorization");
        System.out.println("TOKEN ");
        System.out.println(token);

        if (token != null && !token.isEmpty()) {
            String rawToken = token.substring("Bearer ".length());
            Optional<LoginSession> loginSession = loginSessionRepository.findByToken(rawToken);
            if (loginSession.isPresent()) {
                Set<SimpleGrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority("USER"));
                Authentication authentication = new UsernamePasswordAuthenticationToken(loginSession.get().getUser(), "",authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
