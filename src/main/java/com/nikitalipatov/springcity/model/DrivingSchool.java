package com.nikitalipatov.springcity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "driving_school")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class DrivingSchool {

    @Id
    @SequenceGenerator(name = "driving_school", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driving_school")
    private int id;
    private int pupilCert;
    private String pupilFullName;
}
