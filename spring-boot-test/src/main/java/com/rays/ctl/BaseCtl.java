package com.rays.ctl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.rays.common.ORSResponse;



public class BaseCtl {
     
	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res=new ORSResponse();
		
		if(bindingResult.hasErrors()) {
			res.setSuccess(false);
			Map<String, String> errors=new HashMap<String, String>();
			List<FieldError> list  =bindingResult.getFieldErrors();
			list.forEach(e->{
				errors.put(e.getField(), e.getDefaultMessage());
			});
			res.addInputError(errors);
		}
		
		return res;
		
	}
}
