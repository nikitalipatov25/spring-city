package com.nikitalipatov.springcity.keys;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class PassportId implements Serializable {

    private int serial;
    private int number;
}
