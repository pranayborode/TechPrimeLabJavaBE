package com.techprimelab.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.ICategoryService;
import com.techprimelab.Models.Category;
import com.techprimelab.Repositories.CategoryRepo;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public List<Category> getAllCategory() {
		List<Category>catList = categoryRepo.findAll();
		return catList;
	}
}
