package com.techprimelab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimelab.Models.Reason;

public interface ReasonRepo extends JpaRepository<Reason, Integer> {

	
}
