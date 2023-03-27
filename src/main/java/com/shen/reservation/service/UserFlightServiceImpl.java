package com.shen.reservation.service;

import com.shen.reservation.model.Flight;
import com.shen.reservation.model.User;
import com.shen.reservation.model.UserFlight;
import com.shen.reservation.repository.UserFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Optional;

@Service
public class UserFlightServiceImpl implements UserFlightService{
    @Autowired
    UserFlightRepository userFlightRepository;

    public UserFlight save(User user, Flight flight, Integer seat){
        UserFlight userFlight = new UserFlight(user,flight,seat);
        return userFlightRepository.save(userFlight);
    }

    public UserFlight save(UserFlight uf){
        return userFlightRepository.save(uf);
    }


    public List<UserFlight> findAll(){
        return userFlightRepository.findAll();
    }

    public void deleteById(Long Id){
        userFlightRepository.deleteById(Id);
    }

    public Integer findRemainingSeat(Long id){
        List<UserFlight> userFlights = userFlightRepository.findByFlight_Id(id);
        return 10-userFlights.size();
    }

    public boolean checkUserBooking(Long id, String username, Integer seat){
        return userFlightRepository.existsByUser_UsernameAndFlight_Id(username, id) ||
                userFlightRepository.existsByFlight_IdAndFlight_UserFlight_Seat(id, seat);
    }

    public boolean userBooked(Long id, String username){
        return userFlightRepository.existsByUser_UsernameAndFlight_Id(username,id);
    }
    public boolean checkSeatAvailable(Long id, Integer seat){
        return userFlightRepository.existsByFlight_IdAndFlight_UserFlight_Seat(id, seat);
    }

    public Optional<UserFlight> findById(Long id){
        return userFlightRepository.findById(id);
    }
}



