package com.vdranik.fr.service.impl;

import com.vdranik.fr.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
//	@Autowired
//	UserDetailsService userDetailsService;
//	@Autowired
//	AuthenticationManager authenticationManager;

	@Override
	public boolean login(final String username, final String password) {
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//		authenticationManager.authenticate(token);
//
//		final boolean result = token.isAuthenticated();
//
//		if (result) {
//			SecurityContextHolder.getContext().setAuthentication(token);
//		}
//
		return true /*result*/;
	}

}
