package com.nikitalipatov.springcity.repos;

import com.nikitalipatov.springcity.models.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {

    //    @Query(value = "SELECT p FROM Person p WHERE p.house.get")
//    List<Person> findAllByStreet(String street);
}
