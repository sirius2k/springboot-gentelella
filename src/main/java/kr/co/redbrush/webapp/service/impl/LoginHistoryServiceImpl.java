package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.AccountRole;
import kr.co.redbrush.webapp.domain.LoginHistory;
import kr.co.redbrush.webapp.domain.SecureAccount;
import kr.co.redbrush.webapp.enums.MessageKey;
import kr.co.redbrush.webapp.enums.Role;
import kr.co.redbrush.webapp.exception.AdminRoleNotFoundException;
import kr.co.redbrush.webapp.exception.PasswordEmptyException;
import kr.co.redbrush.webapp.repository.AccountRepository;
import kr.co.redbrush.webapp.repository.AccountRoleRepository;
import kr.co.redbrush.webapp.repository.LoginHistoryRepository;
import kr.co.redbrush.webapp.service.AccountService;
import kr.co.redbrush.webapp.service.LoginHistoryService;
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
public class LoginHistoryServiceImpl implements LoginHistoryService {
    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Override
    public LoginHistory insert(LoginHistory loginHistory) {
        return loginHistoryRepository.save(loginHistory);
    }
}
