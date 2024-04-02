package com.tt.positionservicenew.repository;

import com.tt.positionservicenew.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByAccountIdIn(List<String> accountIds);
}