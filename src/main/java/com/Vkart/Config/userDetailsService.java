package com.Vkart.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Vkart.Models.User;
import com.Vkart.Service.userService;

public class userDetailsService implements UserDetailsService {
	@Autowired
	private userService userservice;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     User user = userservice.getUserByUserName(username);
     if(user==null) {
    	 throw new UsernameNotFoundException("username not found");
     }
      CustomUserDetail customuserdetail= new CustomUserDetail(user);		
		return customuserdetail;
	}

}
