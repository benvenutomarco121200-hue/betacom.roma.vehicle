package com.betacom.sb.mapping;

import java.util.List;

import com.betacom.sb.dto.output.CategoryDTO;
import com.betacom.sb.models.Category;

public class CategoryMap {

	public static List<CategoryDTO> buildCategoryDTOList(List<Category> lC){
		return lC.stream()
				.map(t -> buildCategoryDTO(t)
						).toList();		
	}
	
	public static CategoryDTO buildCategoryDTO(Category c) {
		return CategoryDTO.builder()
				.id(c.getId())
				.category(c.getCategory())
				.build();
	}
}
