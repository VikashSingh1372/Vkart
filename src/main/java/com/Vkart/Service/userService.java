package com.Vkart.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Vkart.DTO.UserDTO;
import com.Vkart.Models.Roles;
import com.Vkart.Models.User;
import com.Vkart.Repositories.roleRepository;
import com.Vkart.Repositories.userRepository;

@Service
public class userService {
	@Autowired
	private userRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private roleRepository rp;
	
	public User  SaveUser(UserDTO userDto) {
		User user = new User();
		user.setUserEmail(userDto.getUserEmail());
		user.setUserName(userDto.getUserName());
		user.setUserGender(userDto.getUserGender());
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		user.setEnabled(true);
		List<Roles> roles = new ArrayList<>();
		roles.add(rp.getRoleByRoleId(39));
		user.setRoles(roles);
		return userRepo.save(user);
		
		
		
		
	}
	
	public User getUserByUserName(String username) {
		return userRepo.getuserByUserName(username);
		
	}

}
