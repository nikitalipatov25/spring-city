package com.nikitalipatov.springcity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class GAI {

    @Id
    @SequenceGenerator(name = "gai_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gai_seq")
    private int id;
    private int drivingLicense;
    private int drivingCert;
    private String driverFullName;

}
