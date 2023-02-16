package com.nikitalipatov.springcity.models;

import com.nikitalipatov.springcity.keys.PassportId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "passport")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(PassportId.class)
public class Passport {
    @Id
    private int serial;
    @Id
    private int number;
    private String address;
    private String addressFact;
    private String placeOfBirth;
    private Date dateOfBirth;
    private String sex;
    private Date issued;
    private String issuedBy;
}
