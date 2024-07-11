package com.techprimelab.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.IDepartmentService;
import com.techprimelab.Models.Department;
import com.techprimelab.Repositories.DepartmentRepo;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	public List<Department> getAllDepartment() {
		List<Department> depList = departmentRepo.findAll();
		return depList;
	}
}
