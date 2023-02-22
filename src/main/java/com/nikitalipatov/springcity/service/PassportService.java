package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.PassportRecord;
import com.nikitalipatov.springcity.model.Passport;

public interface PassportService {

    Passport create(PassportRecord passportRecord);

    void delete(int id);
}
