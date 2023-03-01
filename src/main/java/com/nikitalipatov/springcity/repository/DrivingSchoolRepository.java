package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.DrivingSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrivingSchoolRepository extends JpaRepository<DrivingSchool, Integer> {

    Optional<DrivingSchool> findByPupilCert(int studentCert);
}
