package kr.co.redbrush.webapp.repository;

import kr.co.redbrush.webapp.domain.AccessHistory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessHistoryRepository extends PagingAndSortingRepository<AccessHistory, Long> {
}