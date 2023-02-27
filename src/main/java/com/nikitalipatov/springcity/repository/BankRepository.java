package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    Optional<Bank> findByAccount(int account);
}
