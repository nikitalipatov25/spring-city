package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.House;
import com.nikitalipatov.springcity.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByFullName(String name);

    // todo убрать *
    @Query(value = "SELECT * FROM person JOIN house_person ON person.id = house_person.p_id WHERE house_person.h_id = ?1" , nativeQuery = true)
    List<Person> findAllByHouseIn(int id);

    // todo убрать *
    @Query(value = "SELECT * FROM person WHERE person.full_name LIKE ?1%", nativeQuery = true)
    List<Person> findAllByFullNameLike(String text);

    @Query(value = "SELECT p.fullName FROM Person p JOIN House h ON p.id = h.id WHERE h.street =?1")
    List<Person> findAllByStreet(String street);

    List<Person> findCarsByFullName(String name);
}
