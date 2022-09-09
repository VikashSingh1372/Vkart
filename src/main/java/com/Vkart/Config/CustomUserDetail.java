package com.Vkart.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Vkart.Models.Roles;
import com.Vkart.Models.User;

public class CustomUserDetail implements UserDetails{
	
private User user;

	public CustomUserDetail(User user) {
	super();
	this.user = user;
}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
   List<Roles> roles=(List<Roles>) user.getRoles();
   List<SimpleGrantedAuthority> sga= new ArrayList<>();
   for(Roles role:roles) {
	   sga.add(new SimpleGrantedAuthority(role.getRoleName()));
   }
   
		return sga;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUserPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
