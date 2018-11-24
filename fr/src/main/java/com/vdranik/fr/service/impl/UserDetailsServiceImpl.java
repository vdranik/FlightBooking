package com.vdranik.fr.service.impl;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {} //implements UserDetailsService {
//	@Autowired
//	UserRepository userRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
//		final User user = userRepo.findByEmail(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found" + username);
//		}
//		return new org.springframework.security.core.userdetails.User(user.getEmail(),
//				user.getPassword(), user.getRoles());
//	}
//
//}
