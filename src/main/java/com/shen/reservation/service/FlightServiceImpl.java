package com.shen.reservation.service;

import com.shen.reservation.model.Flight;
import com.shen.reservation.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    public FlightRepository flightRepository;

    @Override
    public List<Flight> findAllFlights(){
        System.out.println("Entering find All Flights");
        return flightRepository.findAll();

    }

    public Flight save(String flightNumber,Integer capacity, String departure, String arrival, String airline){
        Flight flight = new Flight(flightNumber,capacity,departure, arrival, airline);
        return flightRepository.save(flight);
    }

    public Flight save(Flight flight){
        return flightRepository.save(flight);
    }

    public Optional<Flight> findFlightById(Long id){
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isPresent()){
            return flight;
        }else throw new NoSuchElementException();
    }

}
