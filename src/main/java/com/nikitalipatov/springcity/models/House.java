package com.nikitalipatov.springcity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "house")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String city;
    private String street;
    private String number;

}
