package com.techprimelab.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.ITypeService;
import com.techprimelab.Models.Type;
import com.techprimelab.Repositories.TypeRepo;

@Service
public class TypeService implements ITypeService {

	@Autowired
	private TypeRepo typeRepo;
	
	@Override
	public List<Type> getAllType() {
		List<Type> typeList =typeRepo.findAll();
		return typeList;
	}

}
