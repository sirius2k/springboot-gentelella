package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.enums.Role;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.repository.AccountRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.argThat;
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
    private PasswordEncoder passwordEncoder;

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
        account.setAcid(id);
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

    @Test
    public void testInsertAdmin() {
        Account createdAccount = new Account();
        String encryptedPassword = "encryptedPassword";

        when(accountRoleRepository.findByRoleName(Role.ROLE_ADMIN.getName())).thenReturn(accountRole);
        when(passwordEncoder.encode(account.getPassword())).thenReturn(encryptedPassword);
        when(accountRepository.save(argThat(new ArgumentMatcher<Account>() {
            @Override
            public boolean matches(Object argument) {
                Account account = (Account)argument;

                if (account.getPassword().equals(encryptedPassword)) {
                    return true;
                }

                return false;
            }
        }))).thenReturn(createdAccount);

        Account expectedAccount = accountService.insertAdmin(account);

        assertThat("Unexpected value.", expectedAccount, is(createdAccount));
    }

    @Test
    public void testGetCount() {
        when(accountRepository.count()).thenReturn(1L);

        long count = accountService.getCount();

        assertThat("Unexpected value.", count, is(1L));
    }
}