package kr.co.redbrush.webapp.service;

import kr.co.redbrush.webapp.domain.Account;

public interface AccountService {
    Account insertAdmin(Account account);
    Account update(Account account);
    long getCount();
    void processLoginSuccess(Account account);
}
