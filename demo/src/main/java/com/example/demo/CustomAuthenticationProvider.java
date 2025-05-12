package com.example.demo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;
    public CustomAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Custom authentication logic
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // Here you can add your custom authentication logic
        UserDetails user = userDetailsService.loadUserByUsername(username);
        System.out.println("CustomAuthenticationProvider: authenticate() called");
        if (user == null || user.getPassword().equals(password)) {
             // If authentication fails, throw an exception
            throw new BadCredentialsException("Invalid username or password");
        } else {
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
