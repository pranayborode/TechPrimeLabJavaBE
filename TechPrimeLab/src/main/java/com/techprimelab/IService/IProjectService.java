package com.techprimelab.IService;


import java.util.List;
import java.util.Map;

import com.techprimelab.Models.Project;


public interface IProjectService {

	List<Project> getAllProject();

	Project saveProject(Project p);

	Project updateProject(Project u) throws Exception;

	void deleteProject(int projectId) throws Exception;

	Project findProjectById(int projectId) throws Exception;

	Project updateProjectStatus(int projectId, int newStatusId);

	int getTotalProjectCount();

	List<Object[]> calculateProjectsByDepartment();

}


//int countProjectsByStatusId(int statusId);

//List<Object> plist();

//public Map<String, Double> calculateSuccessPercentageByDepartment();