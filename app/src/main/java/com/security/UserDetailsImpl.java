package com.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entities.User;

public class UserDetailsImpl implements UserDetails {

	private User user;
	public UserDetailsImpl(User user) {
		super();
		System.out.println("userdetailsimpl constructor called");
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("getauthorities method called");
		return Collections.singleton(new SimpleGrantedAuthority(user.getClaim()));
	}

	@Override
	public String getPassword() {
		System.out.println("getppassword method called");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		System.out.println("getusernae method called");
		return user.getEmailid();
	}

	@Override
	public boolean isAccountNonExpired() {
		System.out.println("isaccountnonexpired method called");
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		System.out.println("isaccountnonlocked method called");
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		System.out.println("iscredentialsnonexpired method called");
		return true;
	}

	@Override
	public boolean isEnabled() {
		System.out.println("isenabled method called");
		return true;
	}

}
