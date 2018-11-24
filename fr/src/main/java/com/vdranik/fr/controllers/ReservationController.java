package com.vdranik.fr.controllers;

import com.vdranik.fr.dto.ReservationRequest;
import com.vdranik.fr.entities.Flight;
import com.vdranik.fr.entities.Reservation;
import com.vdranik.fr.repos.FlightRepository;
import com.vdranik.fr.service.ReservationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

  @Autowired
  FlightRepository flightRepo;

  @Autowired
  ReservationService reservationService;

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

  @RequestMapping("/showCompleteReservation")
  public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
    LOGGER.info("showCompleteReservation() invoked with the Flight Id: " + flightId);
    Flight flight = flightRepo.findById(flightId).get();
    modelMap.addAttribute("flight", flight);
    LOGGER.info("Flight is: " + flight);
    return "completeReservation";
  }

  @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
  public String completeReservation(ReservationRequest request, ModelMap modelMap) {
    LOGGER.info("completeReservation() " + request);
    Reservation reservation = reservationService.bookFlight(request);
    modelMap.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId());
    return "reservationConfirmation";
  }
}
