package com.techprimelab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimelab.Models.Status;

public interface StatusRepo extends JpaRepository<Status, Integer> {

}
