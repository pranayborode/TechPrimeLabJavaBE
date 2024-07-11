package com.techprimelab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimelab.Models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
