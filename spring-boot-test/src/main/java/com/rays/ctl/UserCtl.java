package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.UserService;

@RestController
@RequestMapping(value="user")
public class UserCtl extends BaseCtl{
    
	@Autowired
	public UserService service;
	
	@PostMapping("submit")
	public ORSResponse save(@RequestBody @Valid UserForm form,BindingResult bindingResult) {
		//ORSResponse res=new ORSResponse();
		ORSResponse res =  validate(bindingResult);
		if(!res.isSuccess()) {
			return res;
		}
		//UserDTO dto= new UserDTO();
		UserDTO dto=(UserDTO)form.getDto();
        
		/*
		 * dto.setId(form.getId()); 
		 * dto.setFirstName(form.getFirstName());
		 * dto.setLastName(form.getLastName()); 
		 * dto.setLoginId(form.getLoginId());
		 * dto.setPassword(form.getPassword());
		 *  dto.setDob(form.getDob());
		 */
		
	      if(dto.getId()!=null && dto.getId()>0) {
	    	  service.update(dto);
	    	  res.addData(dto.getId());
	    	  res.addMessage("Data updated successFully...!!");
	      }else {
	    	 long pk= service.add(dto);
	    	 res.addData(pk);
	    	  res.addMessage("Data added successFully...!!");
	      }
		
		return res;
		
	}
	
	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = service.findById(id);

		res.addData(dto);
		return res;
	}
	@GetMapping("remove/{ids}")
	public ORSResponse remove(@PathVariable long[] ids) {
		ORSResponse res = new ORSResponse();
		for(long id:ids) {
			service.delete(id);
		}
		res.addMessage("user deleted successfully...!!");
		return res;
		
	}
	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody UserForm form,@PathVariable int pageNo) {
		ORSResponse res = new ORSResponse();
		UserDTO dto=new UserDTO();
		
		dto.setFirstName(form.getFirstName());
		 List list=service.search(dto, pageNo, 5);
		 if(list.size()==0) {
			 res.addMessage("Result not found....!!");
		 }else {
			 res.addData(list);
		 }
		return res;
		
	}
	
}
