package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.enums.PreorderStatus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface GAIService {

    CompletableFuture<PreorderStatus> checkDrivingLicense(int drivingLicense, String driverName);

    CompletableFuture<PreorderStatus> checkDrivingCert(int drivingLicense, String driverName) throws ExecutionException, InterruptedException;
}
