package com.techprimelab.Services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techprimelab.IService.IProjectService;
import com.techprimelab.Models.Project;
import com.techprimelab.Models.Status;
import com.techprimelab.Models.StatusConst;
import com.techprimelab.Repositories.ProjectRepo;
import com.techprimelab.Repositories.StatusRepo;
import com.techprimelab.Repositories.TypeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectRepo projectRepo;

	@Autowired
	TypeRepo typeRepo;

	@Autowired
	StatusRepo statusRepo;

	@Override
	public List<Project> getAllProject() {
		List<Project> projectList = projectRepo.findAll();
		return projectList;
	}

	@Override
	public Project saveProject(Project p) {
		Project res = projectRepo.save(p);
		return res;
	}

	@Override
	public Project updateProject(Project p) throws Exception {
		Project res = projectRepo.save(p);
		return res;
	}

	@Override
	public void deleteProject(int projectId) throws Exception {
		findProjectById(projectId);
		projectRepo.deleteById(projectId);

	}

	@Override
	public Project findProjectById(int projectId) throws Exception {
		Optional<Project> opt = projectRepo.findById(projectId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("User not found " + projectId);
	}

	@Override
	public Project updateProjectStatus(int projectId, int newStatusId) {

		Project project = projectRepo.findById(projectId)
				.orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));

		Status status1 = statusRepo.findById(newStatusId)
				.orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + newStatusId));

		project.setStatus(status1);

		return projectRepo.save(project);
	}

	@Override
	public int getTotalProjectCount() {
		return (int) projectRepo.count();
	}

	public int countProjectsByStatusName(String statusName) {
		return projectRepo.countProjectsByStatusName(statusName);
	}

	public long countRunningProjectsWithEndDateBefore(String statusName, LocalDate date) {
        return projectRepo.countRunningProjectsWithEndDateBefore(statusName, date);
    }

	@Override
	public List<Object[]> calculateProjectsByDepartment() {
		 return projectRepo.calculateProjectsByDepartment();
	}
	
	
//	@Override
//	public Map<String, Double> calculateSuccessPercentageByDepartment() {
//        List<Object[]> departmentStats = projectRepo.calculateSuccessPercentageByDepartment();
//        Map<String, Double> successPercentageByDepartment = new HashMap<>();
//
//        for (Object[] row : departmentStats) {
//            String department = (String) row[0];
//            Long totalProjects = (Long) row[1];
//            Long closedProjects = (Long) row[2];
//
//            if (totalProjects != 0) {
//                double successPercentage = (closedProjects / (double) totalProjects) * 100;
//                successPercentageByDepartment.put(department, successPercentage);
//            } else {
//                successPercentageByDepartment.put(department, 0.0);
//            }
//        }
//        return successPercentageByDepartment;
//    }
	
	
	
}
