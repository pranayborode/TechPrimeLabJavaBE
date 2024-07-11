package com.techprimelab.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.techprimelab.Models.User;

public interface UserRepo extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username LIKE %:term% OR u.email LIKE %:term%")
	public List<User> searchUsers(String term);

	public User findByEmail(String email);

	public User findByContact(String contact);
	

}

//@Query(value = "SELECT * FROM users WHERE email =:email", nativeQuery = true)
//public User findByEmail(@Param("email") String email);

//@Query(value = "SELECT * FROM users WHERE contact =:contact", nativeQuery = true)
//public User findByContact(@Param("contact") String contact);