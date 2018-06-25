package kr.co.redbrush.webapp.repository;

import kr.co.redbrush.webapp.domain.AccountRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends PagingAndSortingRepository<AccountRole, Long> {
    AccountRole findByRoleName(String roleName);
}