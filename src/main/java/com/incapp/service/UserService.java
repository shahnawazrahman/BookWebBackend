package com.incapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incapp.entity.User;
import com.incapp.repo.UserRepo;


@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public boolean saveUser(User user) {
		User u=userRepo.findById(user.getEmail()).orElse(null);
		if(u==null) {
			userRepo.save(user);
			return true;
		}else {
			return false;
		}
	}

	public User checkLogin(String email, String password) {
		User u=userRepo.findById(email).orElse(null);
		if(u!=null && password.equals(u.getPassword())) {
			return u;
		}else {
			return null;
		}
		
	}
}
