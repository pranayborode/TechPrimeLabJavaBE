package com.techprimelab.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techprimelab.IService.IUserService;
import com.techprimelab.Models.Project;
import com.techprimelab.Models.User;
import com.techprimelab.Repositories.UserRepo;
import com.techprimelab.dto.ChangePasswordRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserRepo userRepo;

//	@Autowired
//	private HttpServletRequest request;

	HttpSession session;

	@GetMapping("/getAllUser")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User u) {
		User res = null;
		try {
			res = userService.saveUser(u);
			System.out.println("User saved..." + u);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User u) {
		User res = null;
		try {
			res = userService.updateUser(u);	
			System.out.println("User updated..." + u);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}

	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		try {
			userService.deleteUser(userId);
			System.out.println("User Deleted: " + userId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Delete Successfully";
	}

	@GetMapping("/findUserById/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable Long userId) throws Exception {
		User user = userService.findUserById(userId);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/findByGmail/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email) throws Exception {
		User user = userRepo.findByEmail(email);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/findByContact/{contact}")
	public ResponseEntity<User> findByContact(@PathVariable String contact) throws Exception {
		User user = userRepo.findByContact(contact);
		return ResponseEntity.ok(user);
	}



	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
		session = request.getSession(true);
		System.out.println("login..." + session);
		User user1 = userService.authenticate(user.getEmail(), user.getPassword());
		if (user1 != null) { // Check if user exists
			// Store user's email directly in the session
			session.setAttribute("user", user1.getEmail());

			System.out.println("User logged in: " + user1.getEmail());
			return ResponseEntity.ok()
					.body("{\"status\": \"success\", \"message\": \"Login successful\",\"userRole\": \""
							+ user1.getUserRole() + "\"}");
		} else {
			// Authentication failed
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("{\"status\": \"error\", \"message\": \"Invalid username or password\"}");
		}
	}



	@GetMapping("/getSession")
	public ResponseEntity<User> getSession(HttpServletRequest request) {
		// session = request.getSession(false);
		System.out.println("getSession..." + session);
		System.out.println(session.isNew());
		if (session != null) {
			String userEmail = (String) session.getAttribute("user");
			if (userEmail != null) {
				// Session exists and user is logged in
				System.out.println("User retrieved from session: " + userEmail);
				User user = userService.findByEmail(userEmail);
				if (user != null) {
					return ResponseEntity.ok().body(user);
				} else {
					// User not found in database
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
				}
			} else {
				// User is not logged in
				System.out.println("No active session or user not logged in");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}
		} else {
			// No session exists
			System.out.println("No session exists");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpSession session) {
		// session = request.getSession(false);
		System.out.println("logout..." + session);

		if (session != null) {
			session.invalidate();
			System.out.println("User logged out");
			return ResponseEntity.ok().body("{\"status\": \"logoutsuccess\", \"message\": \"Logout successful\"}");
		} else {
			// No session exists
			System.out.println("No session exists");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	@GetMapping("/search")
	public List<User> searchUsers(@RequestParam String term) {
		return userService.searchUsers(term);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
		try {
			// Authenticate the user (You can use your existing authentication logic)
			User user = userService.authenticate(request.getEmail(), request.getCurrentPassword());
			if (user != null) {
				// Update the user's password
				user.setPassword(request.getNewPassword());
				userService.updateUser(user);
				System.out.println("Password Changed..."+request);
				return ResponseEntity.ok().body("Password changed successfully.");
			} else {
				// Authentication failed
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or current password.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred while changing password.");
		}
	}
	
	 @GetMapping("/getAssignedProjects/{userId}")
	    public ResponseEntity<List<Project>> getAssignedProjectsForUser(@PathVariable long userId) {
	        List<Project> assignedProjects = userService.getAssignedProjectsForUser(userId);
	        if (assignedProjects != null) {
	            return ResponseEntity.ok(assignedProjects);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	    }

}



//@PostMapping("/login")
//public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request) {
//  session = request.getSession(true);
//  System.out.println("login..." + session);
//  User user1 = userService.authenticate(user.getEmail(), user.getPassword());
//  if (user1.getEmail() != null) {
//    // Store user's email directly in the session
//    session.setAttribute("user", user1.getEmail());
//    System.out.println("User logged in: " + user1.getEmail());
//    return ResponseEntity.ok()
//        .body("{\"status\": \"success\", \"message\": \"Login successful\",\"userRole\": \""
//            + user.getUserRole() + "\"}");
//  } else {
//    // Authentication failed
//    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//        .body("{\"status\": \"error\", \"message\": \"Invalid username or password\"}");
//  }
//}
//

// Login endpoint
//@PostMapping("/login")
//  public ResponseEntity<User> loginUser(@RequestBody User user) {
//  	
//      try {
//          // Find user by email
//          User existingUser = userRepo.findByEmail(user.getEmail());
//          if (existingUser == null) {
//          	System.out.println("Error while login..");
//              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//          }
//          // Check if passwords match
//          if (!existingUser.getPassword().equals(user.getPassword())) {
//          	System.out.println("Error while login..");
//              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//          }
//          System.out.println(existingUser);
//          System.out.println("Login api working..");
//          return ResponseEntity.ok(existingUser);
//      } catch (Exception e) {
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//      }
//  }

//@GetMapping("/getSession")
//public ResponseEntity<Map<String, String>> getSession(HttpServletRequest request) {
//  // session = request.getSession(false);
//  System.out.println("getSession..." + session);
//  System.out.println(session.isNew());
//  if (session != null) {
//    String userEmail = (String) session.getAttribute("user");
//    
//    if (userEmail != null) {
//      // Session exists and user is logged in
//      System.out.println("User retrieved from session: " + userEmail);
//      Map<String, String> responseData = new HashMap<>();
//      responseData.put("email", userEmail);
//      
//      return ResponseEntity.ok().body(responseData);
//    } else {
//      // User is not logged in
//      System.out.println("No active session or user not logged in");
//      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//    }
//  } else {
//    // No session exists
//    System.out.println("No session exists");
//    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//  }
//}