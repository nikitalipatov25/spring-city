package com.nikitalipatov.springcity.services;

import com.nikitalipatov.springcity.dtos.PassportRecord;
import com.nikitalipatov.springcity.models.Passport;

public interface PassportService {

    Passport create(PassportRecord passportRecord);

    void delete(int id);
}