package kr.co.redbrush.webapp.repository;

import kr.co.redbrush.webapp.domain.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
    Account findAccountByUserId(String userId);
    Account findAccountByEmail(String email);
    long count();
}