package com.techprimelab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimelab.Models.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
