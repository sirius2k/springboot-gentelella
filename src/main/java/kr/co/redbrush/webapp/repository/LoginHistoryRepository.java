package kr.co.redbrush.webapp.repository;

import kr.co.redbrush.webapp.domain.LoginHistory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends PagingAndSortingRepository<LoginHistory, Long> {
}