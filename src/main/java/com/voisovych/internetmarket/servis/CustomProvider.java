package com.voisovych.internetmarket.servis;

import com.voisovych.internetmarket.model.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        if (!name.equals("")) {
            return null;
        }
        String password = authentication.getCredentials().toString();
        if (password.equals("super")) {
            return new UsernamePasswordAuthenticationToken(name, password, Collections.singleton(new Role(3L, "ROLE_ADMIN")));
        } else {
            throw new BadCredentialsException("wrong pass");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
