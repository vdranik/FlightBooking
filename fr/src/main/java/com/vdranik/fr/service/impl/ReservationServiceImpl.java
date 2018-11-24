package com.vdranik.fr.service.impl;

import com.vdranik.fr.dto.ReservationRequest;
import com.vdranik.fr.entities.Flight;
import com.vdranik.fr.entities.Passenger;
import com.vdranik.fr.entities.Reservation;
import com.vdranik.fr.repos.FlightRepository;
import com.vdranik.fr.repos.PassengerRepository;
import com.vdranik.fr.repos.ReservationRepository;
import com.vdranik.fr.service.ReservationService;
import com.vdranik.fr.util.EmailUtil;
import com.vdranik.fr.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.vdranik.fr.itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	FlightRepository flightRepo;

	@Autowired
	PassengerRepository passengerRepo;

	@Autowired
	ReservationRepository reservationRepo;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		// Make Payment

		Long flightId = request.getFlightId();
		Flight flight = flightRepo.findById(flightId).get();

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepo.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		Reservation savedReservation = reservationRepo.save(reservation);
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
