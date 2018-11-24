package com.vdranik.fr.service;

import com.vdranik.fr.dto.ReservationRequest;
import com.vdranik.fr.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}
