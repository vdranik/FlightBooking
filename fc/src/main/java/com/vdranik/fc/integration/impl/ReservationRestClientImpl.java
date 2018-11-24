package com.vdranik.fc.integration.impl;

import com.vdranik.fc.integration.ReservationRestClient;
import com.vdranik.fc.integration.dto.Reservation;
import com.vdranik.fc.integration.dto.ReservationUpdateRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

	private static String RESERVATION_REST_URL = "http://localhost:8080/fr/reservations/";

	@Override
	public Reservation findReservation(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.getForObject(RESERVATION_REST_URL + id, Reservation.class);
		return reservation;
	}

	@Override
	public Reservation updateReservation(ReservationUpdateRequest reservationUpdateRequest) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.postForObject(RESERVATION_REST_URL, reservationUpdateRequest, Reservation.class);
		return reservation;
	}

}
