package com.betacom.sb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.sb.models.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long>{
	Optional<Category> findByCategoryIgnoreCase(String category);
	Boolean existByCategory(String category);

}
