package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.enums.MessageKey;
import kr.co.redbrush.webapp.enums.Role;
import kr.co.redbrush.webapp.exception.AdminRoleNotFoundException;
import kr.co.redbrush.webapp.exception.PasswordEmptyException;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.repository.AccountRoleRepository;
import kr.co.redbrush.webapp.service.AccountService;
import kr.co.redbrush.webapp.service.MessageSourceService;
import lombok.extern.slf4j.Slf4j;
import org.parboiled.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("userDetailsService")
@Slf4j
public class AccountServiceImpl implements UserDetailsService, AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSourceService messageSourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("loadUserByUsername username : {}", username);

        Optional<Account> optionalAccount = Optional.ofNullable(accountRepository.findAccountByUserId(username));

        if (optionalAccount.isPresent()) {
            return new SecureAccount(optionalAccount.get());
        } else {
            throw new UsernameNotFoundException("username = " + username);
        }
    }

    @Override
    public Account insertAdmin(Account account) {
        addAdminRole(account);

        if (StringUtils.isNotEmpty(account.getPassword())) {
            String encryptedPassword = passwordEncoder.encode(account.getPassword());

            account.setPassword(encryptedPassword);

            return accountRepository.save(account);
        } else {
            throw new PasswordEmptyException(messageSourceService.getMessage(MessageKey.PASSWORD_EMPTY));
        }
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    private void addAdminRole(Account account) {
        List<AccountRole> roles = new ArrayList<>();
        AccountRole adminRole = accountRoleRepository.findByRoleName(Role.ROLE_ADMIN.getName());

        LOGGER.debug("Admin role : {}", adminRole);

        if (adminRole == null) throw new AdminRoleNotFoundException(messageSourceService.getMessage(MessageKey.ADMIN_ROLE_NOT_FOUND));

        roles.add(adminRole);
        account.setRoles(roles);
    }

    @Override
    public long getCount() {
        return accountRepository.count();
    }
}
