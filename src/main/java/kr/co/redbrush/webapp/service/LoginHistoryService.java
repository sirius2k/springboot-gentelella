package kr.co.redbrush.webapp.service;

import kr.co.redbrush.webapp.domain.AccessHistory;

public interface LoginHistoryService {
    AccessHistory insert(AccessHistory accessHistory);
}
