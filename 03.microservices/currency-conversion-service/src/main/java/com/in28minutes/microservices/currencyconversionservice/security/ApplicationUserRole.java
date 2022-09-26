package com.in28minutes.microservices.currencyconversionservice.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.in28minutes.microservices.currencyconversionservice.security.ApplicationUserPermission.*;

import com.google.common.collect.Sets;


public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(
			COURSE_READ,
			COURSE_WRITE,
			COURSE_READ,
			COURSE_READ
			));
	
	private final Set<ApplicationUserPermission> permissions;
	
	ApplicationUserRole(Set<ApplicationUserPermission> permissions){
		this.permissions=permissions;
	}
	
	public Set<ApplicationUserPermission> getPermission(){
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getAuthority(){
		Set<SimpleGrantedAuthority> permissions= 
				getPermission().stream().map(p-> new SimpleGrantedAuthority(p.getPermission()))
		.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
