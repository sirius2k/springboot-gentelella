package kr.co.redbrush.webapp.admin.repository;

import kr.co.redbrush.webapp.admin.domain.RandomData;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface RandomDataRepository extends PagingAndSortingRepository<RandomData, Long> {
    RandomData findFirst1ByOrderByCreatedDateDesc();
    List<RandomData> findAllByCreatedDateBetween(Date startDate, Date endDate);
}