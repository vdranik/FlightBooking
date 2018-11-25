package com.vdranik.fr.controllers;

import com.vdranik.fr.entities.User;
import com.vdranik.fr.repos.UserRepository;
import com.vdranik.fr.service.SecurityService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Configuration
public class UserController {

  @Autowired
  private UserRepository userRepository;
  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private SecurityService securityService;


  @RequestMapping("/showReg")
  public String showRegistrationPage() {
    LOGGER.info("Inside showRegistrationPage()");
    return "login/registerUser";
  }

  @RequestMapping("/showLogin")
  public String showLoginPage() {
    LOGGER.info("Inside {} showLoginPage()");
    return "login/login";
  }

  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  public String register(@ModelAttribute("user") User user) {
    LOGGER.info("Inside {} register()" + user);
    user.setPassword(encoder.encode(user.getPassword()));
    userRepository.save(user);
    return "login/login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@RequestParam("email") final String email,
                      @RequestParam("password") final String password,
                      final ModelMap modelMap) {
    LOGGER.info("Inside login() and email is: " + email);

    boolean loginResponse = securityService.login(email, password);
    if (loginResponse) {
      return "findFlights";
    } else {
      modelMap.addAttribute("msg", "Invalid user name or password. Please try again.");
    }
    return "login/login";
  }

}
