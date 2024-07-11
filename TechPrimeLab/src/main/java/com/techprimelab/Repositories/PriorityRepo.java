package com.techprimelab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimelab.Models.Priority;

public interface PriorityRepo extends JpaRepository<Priority, Integer> {

}
