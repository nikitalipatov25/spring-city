package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<BankAccount, Integer> {
}
