package com.vdranik.fc.integration;

import com.vdranik.fc.integration.dto.Reservation;
import com.vdranik.fc.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	public Reservation findReservation(Long id);

	public Reservation updateReservation(ReservationUpdateRequest reservationUpdateRequest);
}
