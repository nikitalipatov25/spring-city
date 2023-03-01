package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.enums.PreorderStatus;

import java.util.concurrent.CompletableFuture;

public interface DrivingSchoolService {

    PreorderStatus checkDrivingCert(int studentCert, String studentName);
}
