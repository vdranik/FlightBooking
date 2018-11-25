package com.vdranik.fr.service.impl;

import com.vdranik.fr.entities.User;
import com.vdranik.fr.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    final User user = userRepo.findByEmail(username);

    if (user == null) {
      throw new UsernameNotFoundException("User not found" + username);
    }

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), user.getRoles());
  }
}
