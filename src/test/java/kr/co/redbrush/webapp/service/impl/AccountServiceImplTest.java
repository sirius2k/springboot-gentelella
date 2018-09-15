package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.enums.Role;
import kr.co.redbrush.webapp.exception.AdminRoleNotFoundException;
import kr.co.redbrush.webapp.exception.PasswordEmptyException;
import kr.co.redbrush.webapp.repository.AccessHistoryRepository;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.repository.AccountRoleRepository;
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
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
public class AccountServiceImplTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    public AccountServiceImpl accountService = new AccountServiceImpl();

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountRoleRepository accountRoleRepository;

    @Mock
    private AccessHistoryRepository accessHistoryRepository;

    @Mock
    private MessageSourceService messageSourceService;

    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Account account;
    private Long id = 1L;
    private String userId = "userId";
    private String name = "name";
    private String password = "password";
    private String email = "test@test.com";

    private AccountRole accountRole;
    private String roleName = Role.ROLE_ADMIN.getName();

    @Before
    public void before() {
        account = new Account();
        account.setId(id);
        account.setUserId(userId);
        account.setName(name);
        account.setPassword(password);
        account.setEmail(email);

        accountRole = new AccountRole();
        accountRole.setArid(id);
        accountRole.setRoleName(roleName);

        List<AccountRole> roles = new ArrayList<>();
        roles.add(accountRole);

        account.setRoles(roles);
    }

    @Test
    public void testLoadUserByUsername() {
        when(accountRepository.findAccountByUserId(userId)).thenReturn(account);

        UserDetails userDetails = accountService.loadUserByUsername(userId);

        LOGGER.debug("userDetails : {}", userDetails);

        assertThat("Unexpected value.", userDetails, instanceOf(SecureAccount.class));
        assertThat("Unexpected value.", userDetails.getUsername(), is(userId));
        assertThat("Unexpected value.", userDetails.getPassword(), is(password));
        assertThat("Unexpected value.", userDetails.getAuthorities(), notNullValue());
        assertThat("Unexpected value.", userDetails.getAuthorities().size(), is(account.getRoles().size()));
        assertThat("Unexpected value.", userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")), is(true));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameThrowsUserNameNotFoundException() {
        when(accountRepository.findAccountByUserId(userId)).thenReturn(account);

        accountService.loadUserByUsername(null);
    }

    @Test
    public void testAccount() {
        when(accountRepository.findAccountByUserId(userId)).thenReturn(account);

        Account expectedAccount = accountService.getAccount(userId);

        assertThat("Unexpected value.", expectedAccount, is(account));
    }

    @Test
    public void testInsertAdmin() {
        when(accountRoleRepository.findByRoleName(Role.ROLE_ADMIN.getName())).thenReturn(accountRole);
        when(accountRepository.save(argThat(new ArgumentMatcher<Account>() {
            @Override
            public boolean matches(Account account) {
                if (passwordEncoder.matches(password, account.getPassword()) &&
                        account.getPasswordFailureCount() == 0 &&
                        account.getPasswordUpdatedDate() != null &&
                        account.isActivated()) {
                    return true;
                }

                return false;
            }
        }))).thenReturn(account);

        Account expectedAccount = accountService.insertAdmin(account);

        assertThat("Unexpected value.", expectedAccount, is(account));
    }

    @Test(expected = PasswordEmptyException.class)
    public void testInsertAdminWithEmptyPassword() {
        account.setPassword(null);

        when(accountRoleRepository.findByRoleName(Role.ROLE_ADMIN.getName())).thenReturn(accountRole);

        accountService.insertAdmin(account);
    }

    @Test(expected = AdminRoleNotFoundException.class)
    public void testInsertAdminShouldReturnAdminRoleNotFoundException() {
        when(accountRoleRepository.findByRoleName(Role.ROLE_ADMIN.getName())).thenReturn(null);

        accountService.insertAdmin(account);
    }

    @Test
    public void testGetCount() {
        when(accountRepository.count()).thenReturn(1L);

        long count = accountService.getCount();

        assertThat("Unexpected value.", count, is(1L));
    }

    @Test
    public void testUpdate() {
        when(accountRepository.save(account)).thenReturn(account);

        Account expectedAccount = accountService.update(account);

        assertThat("Unexpected value.", account, is(expectedAccount));
    }

    @Test
    public void testProcessLoginSuccess() throws Exception {
        accountService.processLoginSuccess(account);

        verify(accessHistoryRepository).save(argThat(accessHistory ->
            accessHistory.getAccount() == account && accessHistory.isLoggedIn() == true
        ));
        verify(accountRepository).save(argThat(account -> account.getLastLogin() != null));
    }
}