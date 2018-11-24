package com.vdranik.fr.controllers;

import com.vdranik.fr.entities.Flight;
import com.vdranik.fr.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {
  @Autowired
  FlightRepository flightRepo;

  @RequestMapping("findFlights")
  public String findFlights(@RequestParam("from") String from,
                            @RequestParam("to") String to,
                            @DateTimeFormat(pattern = "MM-dd-yyyy") @RequestParam("departureDate") Date departureDate,
                            ModelMap modelMap) {

    List<Flight> flights = flightRepo.findFlights(from, to, departureDate);
    modelMap.addAttribute("flights", flights);

    return "displayFlights";
  }

  @RequestMapping("admin/showAddFlight")
  public String showAddFlight() {
    return "addFlight";
  }
}
