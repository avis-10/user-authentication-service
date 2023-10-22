package com.avis.services.userauthenticationservice.security.service;

import com.avis.services.userauthenticationservice.security.repo.AuthUser;
import com.avis.services.userauthenticationservice.security.repo.AuthUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthUserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<AuthUser> optUser = repo.findById(username);
		if (optUser.isPresent()) {
			return new User(optUser.get().getUsername(), optUser.get().getEncryptedPassword(), new ArrayList<>());
		} else {
			System.out.println("No User Found");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
