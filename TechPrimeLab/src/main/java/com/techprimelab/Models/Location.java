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
@Table(name = "location")
public class Location {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
	private int locationId;
	
	 @Column(name = "location_name")
	private String locationName;
}
