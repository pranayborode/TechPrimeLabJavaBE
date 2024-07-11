package com.techprimelab.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.query.ReturnableType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.techprimelab.IService.ICategoryService;
import com.techprimelab.IService.IDepartmentService;
import com.techprimelab.IService.IDivisionService;
import com.techprimelab.IService.ILocationService;
import com.techprimelab.IService.IPriorityService;
import com.techprimelab.IService.IProjectService;
import com.techprimelab.IService.IReasonService;
import com.techprimelab.IService.IStatusService;
import com.techprimelab.IService.ITypeService;
import com.techprimelab.IService.IUserService;
import com.techprimelab.Models.Category;
import com.techprimelab.Models.Department;
import com.techprimelab.Models.Division;
import com.techprimelab.Models.Location;
import com.techprimelab.Models.Priority;
import com.techprimelab.Models.Project;
import com.techprimelab.Models.Reason;
import com.techprimelab.Models.Type;
import com.techprimelab.Repositories.ProjectRepo;
import com.techprimelab.Services.ProjectService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IReasonService reasonService;

	@Autowired
	private ITypeService typeService;

	@Autowired
	private IPriorityService priorityService;

	@Autowired
	private ILocationService locationService;

	@Autowired
	private IDivisionService divisionService;

	@Autowired
	private IDepartmentService departmentService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IStatusService statusService;

	@Autowired
	private ProjectService proService;

	@Autowired
	public ProjectController(ProjectService proService) {
		this.proService = proService;
	}

	@Autowired
	ProjectRepo projectRepo;

	@GetMapping("/getAllProject")
	List<Project> getAllProject() {
		return projectService.getAllProject();
	}

	@PostMapping("/saveProject")
	public Project saveProject(@RequestBody Project p) {
		System.out.println("Inside save project...");
		System.out.println("Project..." + p);
		Project res = null;
		try {
			res = projectService.saveProject(p);
			System.out.println("Project saved..." + p);
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}

	@PutMapping("/updateProject")
	public Project updateProject(@RequestBody Project p) {
		Project res = null;
		try {
			res = projectService.updateProject(p);
			System.out.println("Project updated..." + p);
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}

	@DeleteMapping("/deleteProject/{projectId}")
	public String deleteProject(@PathVariable int projectId) throws Exception {

		try {
			projectService.deleteProject(projectId);
			System.out.println("Project Deleted: " + projectId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Delete Successfully";
	}

	@GetMapping("/findProjectById/{projectId}")
	public ResponseEntity<Project> findProjectById(@PathVariable int projectId) throws Exception {
		Project pro = projectService.findProjectById(projectId);
		return ResponseEntity.ok(pro);
	}

	@GetMapping("/reasons")
	public List<Reason> getAllReason() {
		return reasonService.getAllReason();
	}

	@GetMapping("/type")
	public List<Type> getAllType() {
		return typeService.getAllType();
	}

	@GetMapping("/priority")
	public List<Priority> getAllPriority() {
		return priorityService.getAllPriority();
	}

	@GetMapping("/location")
	public List<Location> getAllLocation() {
		return locationService.getAllLocation();
	}

	@GetMapping("/division")
	public List<Division> getAllDivision() {
		return divisionService.getAllDivision();
	}

	@GetMapping("/department")
	public List<Department> getAllDepartment() {
		return departmentService.getAllDepartment();
	}

	@GetMapping("/category")
	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}

	@GetMapping("/reason/{reasonId}")
	public ResponseEntity<String> getReasonNameById(@PathVariable int reasonId) {
		String reasonName = reasonService.getReasonNameById(reasonId);
		if (reasonName != null) {
			return ResponseEntity.ok(reasonName);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/updateProjectStatus/{projectId}")
	public ResponseEntity<Project> updateProjectStatus(@PathVariable int projectId, @RequestParam int newStatusId) {
		try {
			Project updatedProject = projectService.updateProjectStatus(projectId, newStatusId);
			return ResponseEntity.ok(updatedProject);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/countByStatusName/{statusName}")
	public ResponseEntity<Integer> countProjectsByStatusName(@PathVariable String statusName) {
		int count = projectRepo.countProjectsByStatusName(statusName);
		return ResponseEntity.ok(count);
	}

	@GetMapping("/getTotalProjectCount")
	public int getTotalProjectCount() {
		return projectService.getTotalProjectCount();
	}

	@GetMapping("/countRunningProjects/{statusName}/{date}")
	public ResponseEntity<Long> countRunningProjectsWithEndDateBefore(@PathVariable String statusName,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

		long count = proService.countRunningProjectsWithEndDateBefore(statusName, date);
		return ResponseEntity.ok(count);
	}

//	@GetMapping("/successPercentageByDepartment")
//	public Map<String, Double> getSuccessPercentageByDepartment() {
//		return projectService.calculateSuccessPercentageByDepartment();
//	}

	@GetMapping("/getProjectData")
	public ResponseEntity<Map<String, Object>> getProjectData() {
		Map<String, Object> response = new HashMap<>();
		List<Object[]> projectData = projectService.calculateProjectsByDepartment();
		List<String> departments = new ArrayList<>();
		List<Integer> totalProjects = new ArrayList<>();
		List<Integer> closedProjects = new ArrayList<>();
		for (Object[] data : projectData) {
			departments.add((String) data[0]);
			totalProjects.add((Integer) data[1]);
			closedProjects.add((Integer) data[2]);
		}
		response.put("departments", departments);
		response.put("totalProjects", totalProjects);
		response.put("closedProjects", closedProjects);
		return ResponseEntity.ok(response);
	}

}
