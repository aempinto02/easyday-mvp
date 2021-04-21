package com.easyday.mvp.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.easyday.mvp.domain.Usuario;
import com.easyday.mvp.resources.exception.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, Usuario> {
	
	@Override
	public void initialize(ClienteInsert ann) {}
	
	@Override
	public boolean isValid(Usuario objeto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		return list.isEmpty();
	}
}