package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.dto.PassportRecord;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.Passport;
import com.nikitalipatov.springcity.repository.PassportRepository;
import com.nikitalipatov.springcity.service.PassportService;
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
