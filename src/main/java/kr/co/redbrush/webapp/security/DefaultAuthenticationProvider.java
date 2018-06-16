package kr.co.redbrush.webapp.security;

import kr.co.redbrush.webapp.service.impl.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
        UserDetails userDetails = accountServiceImpl.loadUserByUsername(token.getName());

        if (userDetails == null) {
            LOGGER.info("Invalid username : {}", token.getName());

            throw new UsernameNotFoundException(token.getName());
        }

        if (!matchPassword(userDetails.getPassword(), token.getCredentials())) {
            throw new BadCredentialsException("Username or password was not matched.");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), userDetails.getAuthorities());
    }

    private boolean matchPassword(String password, Object credentials) {
        return password.equals(credentials);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
