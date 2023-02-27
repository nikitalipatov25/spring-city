package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.DrivingSchool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivingSchoolRepository extends JpaRepository<DrivingSchool, Integer> {
}
