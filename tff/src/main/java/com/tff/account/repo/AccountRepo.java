package com.tff.account.repo;

import com.tff.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * see:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, String> {

    Account findAccountById(String id);

    Account findAccountByEmail(String email);

    Account findAccountByPhoneNumber(String phoneNumber);

    @Query("select account from Account account where account.phoneNumber = ?1")
    Account findAccountByPhoneNumberCustom(String phoneNumber);
}
