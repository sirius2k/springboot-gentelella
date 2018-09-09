package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.AccessHistory;
import kr.co.redbrush.webapp.repository.LoginHistoryRepository;
import kr.co.redbrush.webapp.service.LoginHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userDetailsService")
@Slf4j
public class LoginHistoryServiceImpl implements LoginHistoryService {
    @Autowired
    private LoginHistoryRepository loginHistoryRepository;

    @Override
    public AccessHistory insert(AccessHistory accessHistory) {
        return loginHistoryRepository.save(accessHistory);
    }
}
