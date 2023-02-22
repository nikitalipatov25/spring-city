package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByFullName(String name);

    @Query(value = "SELECT person.id, person.full_name, person.age, person.passport_id FROM person JOIN FETCH house_person ON person.id = house_person.p_id WHERE house_person.h_id = ?1" , nativeQuery = true)
    List<Person> findAllByHouseIn(int id);

    @Query(value = "SELECT person.id, person.full_name, person.age, person.passport_id, passport.number, passport.serial, passport.sex FROM person JOIN passport on person.id = passport.id WHERE person.full_name LIKE ?1%", nativeQuery = true)
    List<Person> findPassportDataByFullNameLike(String text);

    @Query(value = "SELECT DISTINCT person.id, person.full_name, person.age, person.passport_id FROM house_person JOIN house ON house.id = house_person.h_id JOIN person ON person.id = house_person.p_id WHERE house.street =?1", nativeQuery = true)
    List<Person> findAllByStreet(String street);

    @Query(value = "SELECT DISTINCT person.id, person.full_name, person.age, person.passport_id FROM person JOIN car ON person.id = car.person_id WHERE person.full_name = ?1", nativeQuery = true)
    List<Person> findCarsByFullName(String name);

    @Query(value = "SELECT DISTINCT person.id, person.full_name, person.age, person.passport_id FROM person JOIN house_person ON person.id = house_person.p_id WHERE person.full_name =?1", nativeQuery = true)
    List<Person> findHousesByFullName(String name);
}
