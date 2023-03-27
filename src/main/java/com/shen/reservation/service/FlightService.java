package com.shen.reservation.service;

import com.shen.reservation.model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    public Flight save(String flightNumber,Integer capacity, String departure, String arrival, String airline);
    public List<Flight> findAllFlights();
    public Optional<Flight> findFlightById(Long id);

}
