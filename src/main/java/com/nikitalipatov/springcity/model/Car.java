package com.nikitalipatov.springcity.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String gosNumber;
    private String model;
    private String name;
    private String type;
    private String color;
    @ManyToOne()
    @JoinColumn(name = "person_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 10)
    private Person person;

    public Car(String gosNumber, String model, String name, String type, String color) {
        this.gosNumber = gosNumber;
        this.model = model;
        this.name = name;
        this.type = type;
        this.color = color;
    }
}
