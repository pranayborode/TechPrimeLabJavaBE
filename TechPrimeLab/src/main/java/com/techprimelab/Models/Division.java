package com.techprimelab.Models;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ToString
@Entity
@Table(name = "division")
public class Division {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
	private int divisionId;
	 @Column(name = "division_name")
	private String divisionName;
}
