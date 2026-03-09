package com.juanzubiri.ecommerce.backend.application;

import com.juanzubiri.ecommerce.backend.domain.model.User;
import com.juanzubiri.ecommerce.backend.domain.port.IUserRepository;

public class RegistrationService {
	
	private final IUserRepository iUserReposiory;
	
	public RegistrationService(IUserRepository iUserRepository) {
		
		this.iUserReposiory = iUserRepository;
	}
	
	public User register(User user) {

	    if(iUserReposiory.existsByEmail(user.getEmail())) {
	        throw new RuntimeException("Email already registered");
	    }

	    return iUserReposiory.save(user);
	}

}
