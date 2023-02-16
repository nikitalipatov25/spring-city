package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByFullName(String name);
}
