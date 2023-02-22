package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Integer> {
}
