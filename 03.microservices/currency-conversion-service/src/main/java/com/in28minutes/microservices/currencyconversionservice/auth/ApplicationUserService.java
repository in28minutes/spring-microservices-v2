package com.in28minutes.microservices.currencyconversionservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

	private final ApplicationUserDao applicationUserDao;

	@Autowired
	public ApplicationUserService(@Qualifier("fake") ApplicationUserDao applicationUserDao) {
	 this.applicationUserDao=applicationUserDao;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		ApplicationUser applicationUser = applicationUserDao.selectApplicationUserName(username).orElseThrow(() -> new UsernameNotFoundException("user not found :" + username));
		if(applicationUser==null || !applicationUser.isEnabled() ){
			throw new UsernameNotFoundException(String.format("%s user not found in DB",username));
		}
		return applicationUser;
	}

}
