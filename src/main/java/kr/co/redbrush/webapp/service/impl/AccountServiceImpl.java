package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.enums.Role;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.repository.AccountRoleRepository;
import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.parboiled.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements UserDetailsService, AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public Account insertAdmin(Account account) {
        AccountRole accountRole = accountRoleRepository.findByRoleName(Role.ROLE_ADMIN.getName());
        List<AccountRole> roles = new ArrayList<>();

        roles.add(accountRole);
        account.setRoles(roles);

        if (StringUtils.isNotEmpty(account.getPassword())) {
            String encryptedPassword = passwordEncoder.encode(account.getPassword());

            account.setPassword(encryptedPassword);

            return accountRepository.save(account);
        }

        return null;
    }

    public long getCount() {
        return accountRepository.count();
    }
}
