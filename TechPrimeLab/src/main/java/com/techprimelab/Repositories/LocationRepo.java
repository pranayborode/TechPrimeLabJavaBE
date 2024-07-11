package com.techprimelab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimelab.Models.Location;

public interface LocationRepo extends JpaRepository<Location, Integer>{

}
