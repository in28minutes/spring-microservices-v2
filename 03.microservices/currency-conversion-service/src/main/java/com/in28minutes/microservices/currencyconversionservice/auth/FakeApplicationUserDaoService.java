package com.in28minutes.microservices.currencyconversionservice.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.in28minutes.microservices.currencyconversionservice.security.ApplicationUserRole;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserName(String username) {
		// TODO Auto-generated method stub
		return getApplicationUsers().stream().filter(user->user.getUsername().equals(username)).findFirst();
	}

	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> appUsers=Lists.newArrayList(
				
				new ApplicationUser(
						"imran",
						passwordEncoder.encode("password"),
						ApplicationUserRole.ADMIN.getAuthority(),
						true,
						true,
						true
						,true),
				new ApplicationUser(
						"salman",
						passwordEncoder.encode("password"),
						ApplicationUserRole.STUDENT.getAuthority(),
						true,
						true,
						true
						,true),
				new ApplicationUser(
						"imtiaz",
						passwordEncoder.encode("password"),
						ApplicationUserRole.STUDENT.getAuthority(),
						true,
						true,
						true
						,true),
				new ApplicationUser(
						"ibrahim",
						passwordEncoder.encode("password"),
						ApplicationUserRole.STUDENT.getAuthority(),
						true,
						true,
						true
						,true),
				new ApplicationUser(
						"heena",
						passwordEncoder.encode("password"),
						ApplicationUserRole.STUDENT.getAuthority(),
						true,
						true,
						true
						,true)
				);
		
		return appUsers;
	}
}
