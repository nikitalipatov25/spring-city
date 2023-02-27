package com.nikitalipatov.springcity.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Bank {
    @Id
    @SequenceGenerator(name = "bank_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_seq")
    private int id;

    private String name;

    private double amount;

    @Column(unique = true)
    private int account;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    public Bank(String name, double amount, int account) {
        this.name = name;
        this.amount = amount;
        this.account = account;
    }
}
