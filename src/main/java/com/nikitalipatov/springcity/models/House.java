package com.nikitalipatov.springcity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    public House(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }
}
