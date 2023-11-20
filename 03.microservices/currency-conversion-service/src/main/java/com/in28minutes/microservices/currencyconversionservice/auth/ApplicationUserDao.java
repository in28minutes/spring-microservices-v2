package com.in28minutes.microservices.currencyconversionservice.auth;

import java.util.Optional;

public interface ApplicationUserDao {
Optional<ApplicationUser> selectApplicationUserName(String username);
}
