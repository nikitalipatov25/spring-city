package com.nikitalipatov.springcity.services.impl;

import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.exeptions.ResourceNotFoundException;
import com.nikitalipatov.springcity.models.Passport;
import com.nikitalipatov.springcity.repos.PassportRepository;
import com.nikitalipatov.springcity.services.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Override
    public Passport create(PassportRecord passportRecord) {
        Passport passport = new Passport(passportRecord.serial(), passportRecord.number(),
                passportRecord.address(), passportRecord.addressFact(),
                passportRecord.placeOfBirth(), passportRecord.dateOfBirth(), passportRecord.sex(),
                passportRecord.issued(), passportRecord.issuedBy());
        return passportRepository.save(passport);
    }

    @Override
    public void delete(int passportId) {
        var passport =getPassport(passportId);
        passportRepository.delete(passport);
    }

    public Passport getPassport(int passportId) {
        return passportRepository.findById(passportId).orElseThrow(
                () -> new ResourceNotFoundException("No such passport with id " + passportId)
        );
    }
}
