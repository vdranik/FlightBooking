package com.vdranik.fr.controllers;

import com.vdranik.fr.entities.User;
import com.vdranik.fr.repos.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @RequestMapping("/showReg")
  public String showRegistrationPage() {
    return "login/registerUser";
  }

  @RequestMapping("/showLogin")
  public String showLoginPage() {
    return "login/login";
  }

  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  public String showLoginPage(@ModelAttribute("user") User user) {
    userRepository.save(user);
    return "login/login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@RequestParam("email") final String email, @RequestParam("password") final String password, final ModelMap modelMap) {

    LOGGER.error("ERROR");
    LOGGER.warn("WARNING");
    LOGGER.info("INFO");
    LOGGER.debug("DEBUG");
    LOGGER.trace("TRACE");

    User user = userRepository.findByEmail(email);
    if(user.getPassword().equals(password)){
      return "findFlights";
    } else {
      modelMap.addAttribute("msg", "Invalid username or password. Please try again.");
    }
    //    final boolean loginResponse = securityService.login(email, password);
    //    if (loginResponse) {
    //      return "findFlights";
    //    } else {
    //      modelMap.addAttribute("msg", "Invalid user name or password. Please try again.");
    //    }
    return "login/login";
  }
}
