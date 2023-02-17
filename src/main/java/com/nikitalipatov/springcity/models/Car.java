package com.nikitalipatov.springcity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String gosNumber;
    private String model;
    private String name;
    private String type;
    private String color;

    public Car(String gosNumber, String model, String name, String type, String color) {
        this.gosNumber = gosNumber;
        this.model = model;
        this.name = name;
        this.type = type;
        this.color = color;
    }
}
