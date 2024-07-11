package com.techprimelab.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techprimelab.Models.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

	@Query("SELECT COUNT(p) FROM Project p WHERE p.status.statusName = :statusName")
	int countProjectsByStatusName(@Param("statusName") String statusName);

	@Query("SELECT COUNT(p) FROM Project p WHERE p.status.statusName = :statusName AND p.endDate < :date")
	long countRunningProjectsWithEndDateBefore(@Param("statusName") String statusName, @Param("date") LocalDate date);

	@Query("SELECT p.department.depName, CAST(COUNT(p) AS int) AS totalProjects, "
			+ "CAST(COUNT(CASE WHEN p.status.statusName = 'CLOSED' THEN 1 END) AS int) AS closedProjects "
			+ "FROM Project p GROUP BY p.department")
	List<Object[]> calculateProjectsByDepartment();

	// Query method to fetch projects assigned to a user
	@Query("SELECT p FROM Project p WHERE p.user.userId = :userId")
	List<Project> findProjectsByUserId(@Param("userId") long userId);
}
