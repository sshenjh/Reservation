package com.shen.reservation.service;

import com.shen.reservation.model.Flight;
import com.shen.reservation.model.User;
import com.shen.reservation.model.UserFlight;

public interface UserFlightService {
    UserFlight save(User user, Flight flight, Integer seat);
}
