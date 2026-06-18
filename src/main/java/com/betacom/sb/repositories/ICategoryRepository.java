package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.sb.models.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{
	Optional<Category> findByCategoryIgnoreCase(String category);

}
