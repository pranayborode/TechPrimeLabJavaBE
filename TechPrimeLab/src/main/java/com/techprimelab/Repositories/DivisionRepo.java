package com.techprimelab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimelab.Models.Division;

public interface DivisionRepo extends JpaRepository<Division, Integer> {

}
