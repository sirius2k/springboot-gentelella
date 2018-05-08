package kr.co.redbrush.webapp.service;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUserId(username);

        return new SecureAccount(account);
    }
}
