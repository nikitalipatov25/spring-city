package com.nikitalipatov.springcity.dtos;

import com.nikitalipatov.springcity.models.Passport;

public record PersonRecord(String fullName, int age, PassportRecord passportRecord) {


}
