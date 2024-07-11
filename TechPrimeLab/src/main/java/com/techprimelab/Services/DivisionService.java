package com.techprimelab.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.IDivisionService;
import com.techprimelab.Models.Division;
import com.techprimelab.Repositories.DivisionRepo;

@Service
public class DivisionService implements IDivisionService{

	@Autowired
	private DivisionRepo divisionRepo;

	@Override
	public List<Division> getAllDivision() {
		List<Division> divList = divisionRepo.findAll();
		return divList;
	}
}
