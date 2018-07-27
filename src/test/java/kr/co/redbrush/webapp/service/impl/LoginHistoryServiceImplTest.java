package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.LoginHistory;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.enums.Role;
import kr.co.redbrush.webapp.exception.AdminRoleNotFoundException;
import kr.co.redbrush.webapp.exception.PasswordEmptyException;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.repository.AccountRoleRepository;
import kr.co.redbrush.webapp.repository.LoginHistoryRepository;
import kr.co.redbrush.webapp.service.MessageSourceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.when;

@Slf4j
public class LoginHistoryServiceImplTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    public LoginHistoryServiceImpl loginHistoryService = new LoginHistoryServiceImpl();

    @Mock
    private LoginHistoryRepository loginHistoryRepository;

    private LoginHistory loginHistory;
    private Long id = 1L;
    private Date loginDate = new Date();

    @Before
    public void before() {
        loginHistory = new LoginHistory();
        loginHistory.setId(id);
        loginHistory.setLoginDate(loginDate);
    }

    @Test
    public void testInsert() {
        when(loginHistoryRepository.save(loginHistory)).thenReturn(loginHistory);

        LoginHistory expectedLoginHistory = loginHistoryService.insert(loginHistory);

        LOGGER.debug("loginHistory : {}", expectedLoginHistory);

        assertThat("Unexpected value.", loginHistory, is(expectedLoginHistory));
    }
}