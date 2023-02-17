package com.nikitalipatov.springcity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NamedNativeQuery(name = "Person.findByFullName", query = "SELECT * FROM person INNER JOIN car ON person.id = car.id")

//@NamedQuery(name = "Person_FindAllByFullName",
//        query = "SELECT distinct FROM car_person WHERE p_id=1")
//c1_0.p_id,c1_1.id,c1_1.color,c1_1.gos_number,c1_1.model,c1_1.name,c1_1.type from car_person c1_0 join car c1_1 on c1_1.id=c1_0.c_id where c1_0.p_id=?
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
    @OneToOne(cascade = CascadeType.ALL)
    private Passport passport;
    @ManyToMany()
    @JoinTable(name = "house_person",
            joinColumns = @JoinColumn(name = "p_id"),
            inverseJoinColumns = @JoinColumn(name = "h_id")
    )
    private Set<House> house;
    @OneToMany(cascade = CascadeType.ALL)
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
