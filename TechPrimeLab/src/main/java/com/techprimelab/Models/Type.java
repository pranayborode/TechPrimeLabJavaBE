package com.techprimelab.Models;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "p_type")
public class Type {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
	private int typeId;
	
	@Column(name = "type_name",nullable = false, columnDefinition = "VARCHAR(45)")
	private String typeName;
	
//	@OneToOne
//	@JoinColumn(name="typeId")
//	@MapsId
//	private Project project;
}
