package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.RoleDAO;
import com.rays.dto.RoleDTO;

@Service
@Transactional
public class RoleService {

	@Autowired
	public RoleDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(RoleDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(RoleDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			RoleDTO dto = findById(id);
			dao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public RoleDTO findById(long pk) {
		RoleDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(RoleDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}
	
	@Transactional(readOnly = true)
	public List search(RoleDTO dto, int pageNo, int pageSize) {
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}

	
	
	
}