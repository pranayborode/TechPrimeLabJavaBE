package com.techprimelab.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.IPriorityService;
import com.techprimelab.Models.Priority;

import com.techprimelab.Repositories.PriorityRepo;

@Service
public class PriorityService implements IPriorityService {

	@Autowired
	private PriorityRepo priorityRepo;
	
	@Override
	public List<Priority> getAllPriority() {
		List<Priority> pList =priorityRepo.findAll();
		return pList;
	}

}
