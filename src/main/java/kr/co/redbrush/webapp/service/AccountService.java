package kr.co.redbrush.webapp.service;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("loadUserByUsername username : {}", username);

        Account account = accountRepository.findAccountByUserId(username);

        if (account == null) throw new UsernameNotFoundException("username = " + username);

        return new SecureAccount(account);
    }

    public long getCount() {
        return accountRepository.count();
    }
}
