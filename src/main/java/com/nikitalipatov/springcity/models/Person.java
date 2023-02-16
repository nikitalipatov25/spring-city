package com.nikitalipatov.springcity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name ="person")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String fullName;
    private int age;
    @OneToOne
    private Passport passport;
    @ManyToMany
    @JoinTable(name = "house_person",
            joinColumns = @JoinColumn(name = "p_id"),
            inverseJoinColumns = @JoinColumn(name = "h_id")
    )
    private Set<House> house;
    @OneToMany
    @JoinTable(name = "car_person",
            joinColumns = @JoinColumn(name = "p_id"),
            inverseJoinColumns = @JoinColumn(name = "c_id")
    )
    private Set<Car> car;

    public Person(String fullName, int age, Passport passport) {
        this.fullName = fullName;
        this.age = age;
        this.passport = passport;
    }
}
