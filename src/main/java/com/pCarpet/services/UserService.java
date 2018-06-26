package com.pCarpet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.UserRepository;
import com.pCarpet.model.User;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private static User loggedUser;

	@Autowired 
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Boolean login(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (user == null) {
			return false;
		} else {
			if (user.getPassword().equals(password))
			{
				loggedUser = user;
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public User getLoggedUser()
	{
		return loggedUser;
	}
	
	public void destroyUser()
	{
		loggedUser = null;
	}
}
