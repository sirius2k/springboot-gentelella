package kr.co.redbrush.webapp.service.impl;

import kr.co.redbrush.webapp.domain.AccessHistory;
import kr.co.redbrush.webapp.repository.AccessHistoryRepository;
import kr.co.redbrush.webapp.service.AccessHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userDetailsService")
@Slf4j
public class AccessHistoryServiceImpl implements AccessHistoryService {
    @Autowired
    private AccessHistoryRepository accessHistoryRepository;

    @Override
    public AccessHistory insert(AccessHistory accessHistory) {
        return accessHistoryRepository.save(accessHistory);
    }
}
