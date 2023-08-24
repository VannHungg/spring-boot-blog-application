package com.example.springbootblogapplication.repositories;

import com.example.springbootblogapplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
//    @Query(value = "SELECT * FROM Account u WHERE u.email = ?1", nativeQuery = true)
    Optional<Account> findOneByEmail(String email);
}
