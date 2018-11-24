package com.vdranik.fr.controllers;

import com.vdranik.fr.dto.ReservationUpdateRequest;
import com.vdranik.fr.entities.Reservation;
import com.vdranik.fr.repos.ReservationRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationRestController {
  @Autowired
  ReservationRepository reservationRepo;

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

  @RequestMapping(value = "/reservations/{id}")
  public @ResponseBody Reservation findReservation(@PathVariable("id") Long id) {
    LOGGER.info("Inside findReservation() for id: " + id);
    return reservationRepo.findById(id).get();
  }

  @RequestMapping(value = "/reservations")
  public @ResponseBody Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
    LOGGER.info("Inside updateReservation() for : " + request);
    Reservation reservation = reservationRepo.findById(request.getId()).get();
    reservation.setCheckedIn(Boolean.TRUE);
    reservation.setNumberOfBags(request.getNumberOfBags());
    LOGGER.info("Saving reservation");
    Reservation savedReservation = reservationRepo.save(reservation);
    return savedReservation;
  }
}
