package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByFullName(String name);

    @Query(value = "SELECT person.id, person.full_name, person.age, person.passport_id FROM person JOIN house_person ON person.id = house_person.p_id WHERE house_person.h_id = ?1" , nativeQuery = true)
    List<Person> findAllByHouseIn(int id);

    @Query(value = "SELECT person.id, person.full_name, person.age FROM person WHERE person.full_name LIKE ?1%", nativeQuery = true)
    List<Person> findAllByFullNameLike(String text);

    @Query(value = "SELECT person.id, person.full_name, person.age, person.passport_id FROM house_person JOIN house ON house.id = house_person.h_id JOIN person ON person.id = house_person.p_id WHERE house.street =?1", nativeQuery = true)
    List<Person> findAllByStreet(String street);

    List<Person> findCarsByFullName(String name);
}
