package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.enums.PreorderStatus;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.DrivingSchool;
import com.nikitalipatov.springcity.repository.DrivingSchoolRepository;
import com.nikitalipatov.springcity.service.DrivingSchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrivingSchoolServiceImpl implements DrivingSchoolService {

    private final DrivingSchoolRepository drivingSchoolRepository;

    @Override
    public PreorderStatus checkDrivingCert(int studentCert, String studentName) {
        DrivingSchool student = getStudentByCert(studentCert);
        PreorderStatus schoolPreorderStatus;
        if (student.getPupilCert() == studentCert && student.getPupilFullName().equals(studentName)) {
            schoolPreorderStatus = PreorderStatus.SUCCESS;
        } else {
            schoolPreorderStatus = PreorderStatus.FAIL;
        }
        return schoolPreorderStatus;
    }

    public DrivingSchool getStudentByCert(int studentCert) {
        return drivingSchoolRepository.findByPupilCert(studentCert).orElseThrow(
                () -> new ResourceNotFoundException("No such student with cert " + studentCert)
        );
    }

}
