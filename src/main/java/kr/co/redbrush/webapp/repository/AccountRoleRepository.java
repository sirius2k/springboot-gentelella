package kr.co.redbrush.webapp.repository;

import kr.co.redbrush.webapp.domain.AccountRole;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRoleRepository extends PagingAndSortingRepository<AccountRole, Long> {
}