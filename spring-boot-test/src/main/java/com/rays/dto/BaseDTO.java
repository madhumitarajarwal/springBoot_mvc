package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseDTO {
	
	@Id
	@GeneratedValue(generator="ncspk")
	@GenericGenerator(name="ncspk", strategy="native")
	@Column(name="ID" ,unique=true, nullable=false)
	
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

	
}
