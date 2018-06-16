package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.repository.AccountRoleRepository;
import kr.co.redbrush.webapp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements UserDetailsService, AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("loadUserByUsername username : {}", username);

        Account account = accountRepository.findAccountByUserId(username);

        if (account == null) throw new UsernameNotFoundException("username = " + username);

        return new SecureAccount(account);
    }

    public Account insertAdmin(Account account) {
        // TODO : Implement password generation
        return accountRepository.save(account);
    }

    public long getCount() {
        return accountRepository.count();
    }
}
