package com.nikitalipatov.springcity.repository;

import com.nikitalipatov.springcity.model.Preorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreorderRepository extends JpaRepository<Preorder, Integer> {
}
