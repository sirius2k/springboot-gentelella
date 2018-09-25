package kr.co.redbrush.webapp.repository;

import kr.co.redbrush.webapp.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends PagingAndSortingRepository<Role, Long> {
    Role findByRoleName(String roleName);
}