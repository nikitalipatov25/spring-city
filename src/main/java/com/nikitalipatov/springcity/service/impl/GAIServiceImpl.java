package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.enums.PreorderStatus;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.GAI;
import com.nikitalipatov.springcity.repository.GAIRepository;
import com.nikitalipatov.springcity.service.DrivingSchoolService;
import com.nikitalipatov.springcity.service.GAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class GAIServiceImpl implements GAIService {

    private final GAIRepository gaiRepository;
    private final DrivingSchoolService drivingSchoolService;

    @Override
    public CompletableFuture<PreorderStatus> checkDrivingLicense(int drivingLicense, String driverName) {
        return CompletableFuture.supplyAsync(() -> checkDrivingLicenseTask(drivingLicense, driverName));
    }

    @Override
    public CompletableFuture<PreorderStatus> checkDrivingCert(int drivingLicense, String driverName) {
        return CompletableFuture.supplyAsync(() -> checkDrivingCertTask(drivingLicense, driverName));
    }

    public GAI getDriverByLicense(int drivingLicense) {
        return gaiRepository.findByDrivingLicense(drivingLicense).orElseThrow(
                () -> new ResourceNotFoundException("No such driver with license " + drivingLicense)
        );
    }

    private PreorderStatus checkDrivingLicenseTask(int drivingLicense, String driverName) {
        GAI driver = getDriverByLicense(drivingLicense);
        PreorderStatus gaiStatus;
        if (driver.getDrivingLicense() == drivingLicense && driver.getDriverFullName().equals(driverName)) {
            gaiStatus = PreorderStatus.SUCCESS;
        } else {
            gaiStatus = PreorderStatus.FAIL;
        }
        try {
            log.info("checkDrivingLicense processing ... ");
            Thread.sleep(15000);
            log.info("checkDrivingLicense complete ... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gaiStatus;
    }

    private PreorderStatus checkDrivingCertTask(int drivingLicense, String driverName) {
        GAI driver = getDriverByLicense(drivingLicense);
        try {
            log.info("checkDrivingCert processing ... ");
            Thread.sleep(10000);
            log.info("checkDrivingCert complete ... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return drivingSchoolService.checkDrivingCert(driver.getDrivingCert(), driverName);
    }
}
