package com.techprimelab.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.IStatusService;
import com.techprimelab.Repositories.ProjectRepo;


@Service
public class StatusService implements IStatusService{

	@Autowired
	ProjectRepo projectRepo;

}
