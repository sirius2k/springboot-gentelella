package kr.co.redbrush.webapp.security;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;

@Slf4j
public class DefaultAuthenticationProviderTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    public DefaultAuthenticationProvider defaultAuthenticationProvider = new DefaultAuthenticationProvider();

    @Mock
    private AccountService accountService;

    @Mock
    private UsernamePasswordAuthenticationToken token;

    private String username = "user";
    private String password = "password";
    private String invalidUsername = "invalid";
    private SecureAccount userDetails;

    @Before
    public void before() {
        Account account = new Account();
        account.setUserId(username);
        account.setPassword(password);

        userDetails = new SecureAccount(account);

        when(accountService.loadUserByUsername(username)).thenReturn(userDetails);
        when(accountService.loadUserByUsername(invalidUsername)).thenReturn(null);
    }

    @Test
    public void testAuthenticate() throws Exception {
        Authentication authentication = defaultAuthenticationProvider.authenticate(token);

        LOGGER.debug("authentication : {}", authentication);

        assertThat("Unexpected value.", authentication, notNullValue());
        assertThat("Unexpected value.", authentication.getPrincipal(), is(username));
        assertThat("Unexpected value.", authentication.getCredentials(), is(password));
    }
}