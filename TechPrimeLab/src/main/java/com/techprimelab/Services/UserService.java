package com.techprimelab.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techprimelab.IService.IUserService;
import com.techprimelab.Models.Project;
import com.techprimelab.Models.User;
import com.techprimelab.Repositories.ProjectRepo;
import com.techprimelab.Repositories.UserRepo;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProjectRepo projectRepo;

	@Override
	public List<User> getAllUser() {
		List<User> userList = userRepo.findAll();
		return userList;
	}

	@Override
	public User saveUser(User u) {
		u.setUserRole("user");
		User res = userRepo.save(u);
		return res;
	}

	@Override
	public User updateUser(User u) throws Exception {
		User res = userRepo.save(u);
		return res;
	}

	@Override
	public void deleteUser(Long userId) throws Exception {
		findUserById(userId);
		userRepo.deleteById(userId);

	}

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt = userRepo.findById(userId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("User not found " + userId);
	}

	@Override
	public List<User> searchUsers(String term) {
		return userRepo.searchUsers(term);
	}

	public User authenticate(String email, String password) {
		User user = userRepo.findByEmail(email);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null; // Authentication failed
	}

	@Override
	public User findByEmail(String email) {
		User res = userRepo.findByEmail(email);
		return res;
	}

	@Override
	 public List<Project> getAssignedProjectsForUser(long userId) {
		
		 return projectRepo.findProjectsByUserId(userId);
    }

}

//public User authenticate(String email, String password) {
//User user = userRepo.findByEmail(email);
//if (user != null && email.equals(user.getEmail()) && password.equals(user.getPassword())) {
//  
// user=new User();
// user.setEmail(email);
// user.setPassword(password);
// String userRole = user.getUserRole();
// user.setUserRole(userRole);
// return  user;
//}
//return null; // Authentication failed
//}