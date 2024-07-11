package com.techprimelab.Models;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId")
	private int projectId;

	@Column(name = "projectName", nullable = false)
	private String projectName;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "reason_id")
	private Reason reason;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "type_id")
	private Type type;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "division_id")
	private Division division;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "priority_id")
	private Priority priority;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "dept_id")
	private Department department;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name = "startDate", nullable = false)
	private Date startDate;

	@Column(name = "endDate", nullable = false)
	private Date endDate;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

}

//@OneToMany(mappedBy="project")
//private List<ProjectUser> projectUser;

//@ManyToMany(mappedBy = "projects")     
//private List<User> users;