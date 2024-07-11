package com.techprimelab.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.ILocationService;
import com.techprimelab.Models.Location;
import com.techprimelab.Repositories.LocationRepo;

@Service
public class LocationService implements ILocationService {

	@Autowired
	private LocationRepo locationRepo;

	@Override
	public List<Location> getAllLocation() {
		List<Location> locList =locationRepo.findAll();
		return locList;
	}
	
	
}
