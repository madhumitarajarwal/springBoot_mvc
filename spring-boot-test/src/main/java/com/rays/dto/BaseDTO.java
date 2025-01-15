package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.rays.common.DropDownList;

@MappedSuperclass
public abstract class BaseDTO implements DropDownList  {
	
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
	@Override
	public String getKey() {
		return id + "";
	}
	
	

	
}
