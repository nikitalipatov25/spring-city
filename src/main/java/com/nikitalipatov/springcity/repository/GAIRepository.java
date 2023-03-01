package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.GAI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GAIRepository extends JpaRepository<GAI, Integer> {

    Optional<GAI> findByDrivingLicense(int drivingLicense);
}
