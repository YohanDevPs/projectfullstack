package com.aegro.api.service.subexceptions;

public class EntityNotFoundExcpetion  extends RuntimeException{
	private static final long serialVersionUID = 1L;


	public EntityNotFoundExcpetion(String msg) {
		super(msg);
	}
	
}
