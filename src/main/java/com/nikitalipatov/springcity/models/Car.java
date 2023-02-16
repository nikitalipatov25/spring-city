package com.nikitalipatov.springcity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String gosNumber;
    private String model;
    private String name;
    private String type;
    private String color;
}
