package kr.co.redbrush.webapp.repository;

import kr.co.redbrush.webapp.domain.Account;
import kr.co.redbrush.webapp.domain.RandomData;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Account findAccountByUserId(String userId);
    Account findAccountByEmail(String email);
}