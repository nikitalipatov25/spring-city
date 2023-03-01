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
public class BankAccount {
    @Id
    @SequenceGenerator(name = "bank_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_seq")
    private int id;

    private String name;

    private double balance;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}
