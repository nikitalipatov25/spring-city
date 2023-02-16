package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Integer> {
}
