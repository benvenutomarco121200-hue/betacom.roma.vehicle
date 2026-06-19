package com.betacom.sb.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.betacom.sb.dto.input.CategoryReq;
import com.betacom.sb.dto.output.CategoryDTO;
import com.betacom.sb.mapping.CategoryMap;
import com.betacom.sb.models.Category;
import com.betacom.sb.repositories.ICategoryRepository;
import com.betacom.sb.services.interfaces.ICategoryServices;

import exceptions.BetacomRomaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryImpl implements ICategoryServices{

	private final ICategoryRepository categoryRepo;

	@Override
	public void create(CategoryReq req) throws BetacomRomaException {
		log.debug("create category {}",req);
		if (categoryRepo.existByCategory(req.getCategory()))
			throw new BetacomRomaException("Category_exits");
		
		Category tC = new Category();
		tC.setCategory(req.getCategory());
		categoryRepo.save(tC);
	}

	@Override
	public void delete(Long id) throws BetacomRomaException {
		log.debug("delete {}", id);
		Category tC = categoryRepo.findById(id)
				.orElseThrow(() -> new BetacomRomaException("key_ntfnd"));
		categoryRepo.delete(tC);
	}

	@Override
	public List<CategoryDTO> list() {
		log.debug("List");
		List<Category> lC = categoryRepo.findAll();
		
		return CategoryMap.buildCategoryDTOList(lC);
	}
	
	
}
