package io.crowdcode.flaschenlager.accounting.repository;

import io.crowdcode.flaschenlager.accounting.model.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountEntryRepository extends JpaRepository<AccountEntry, Long> {

    List<AccountEntry> findByCustomerIdAndUserId(@Param("customerId") Long customerId,
                                                 @Param("userId") Long userId);

    List<AccountEntry> findByCustomerId(@Param("customerId") Long customerId);

    List<AccountEntry> findByUserId(@Param("userId") Long userId);
}
