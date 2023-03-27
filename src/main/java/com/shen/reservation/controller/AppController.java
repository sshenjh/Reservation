package com.shen.reservation.controller;

import com.shen.reservation.model.Flight;
import com.shen.reservation.model.User;
import com.shen.reservation.model.UserFlight;
import com.shen.reservation.service.FlightServiceImpl;
import com.shen.reservation.service.UserFlightServiceImpl;
import com.shen.reservation.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {
    @Autowired
    FlightServiceImpl flightServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserFlightServiceImpl userFlightServiceImpl;

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/showLoginForm")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/flights")
    public String showFlights(Model model){
        model.addAttribute("flights",flightServiceImpl.findAllFlights());
        System.out.println("going to return flights html");
        return "flights";
    }

    @GetMapping("/flightInfo/{id}")
    public String showFlightInfo(@PathVariable(value = "id") long id, Model model){
        Optional<Flight> flight = flightServiceImpl.findFlightById(id);
        List<UserFlight> userFlight = userFlightServiceImpl.findAll();
        if(flight.isPresent()){
            model.addAttribute("flight",flight.get());
            model.addAttribute("userFlight",userFlight);
        }
        return "flight-info";
    }

    @GetMapping("/bookFlight/{username}/{id}/{seat}")
    public String bookFlight(@PathVariable(value="username") String username,
                             @PathVariable(value = "id") long id,
                             @PathVariable(value = "seat") Integer seat,Model model){
        Optional<Flight> flight = flightServiceImpl.findFlightById(id);
        Optional<User> user = userServiceImpl.findByUsername(username);
        if(flight.isPresent()){
            Flight theFlight = flight.get();
            model.addAttribute("flight",theFlight);
            if(user.isPresent()){
                User theUser = user.get();
                userFlightServiceImpl.save(theUser,theFlight,seat);

                List<UserFlight> userFlight = userFlightServiceImpl.findAll();
                model.addAttribute("userFlight",userFlight);
            }
        }
        return "flight-info";
    }

    @GetMapping("/cancelFlight/{username}/{id}/{seat}/{ufid}")
    public String cancelFlight(@PathVariable(value="username") String username,
                             @PathVariable(value = "id") long id,
                             @PathVariable(value = "seat") Integer seat,
                               @PathVariable(value ="ufid") Long ufid,
                               Model model){
        Optional<Flight> flight = flightServiceImpl.findFlightById(id);
        Optional<User> user = userServiceImpl.findByUsername(username);
        if(flight.isPresent()){
            Flight theFlight = flight.get();
            model.addAttribute("flight",theFlight);
            if(user.isPresent()){
                User theUser = user.get();
                userFlightServiceImpl.deleteById(ufid);

                List<UserFlight> userFlight = userFlightServiceImpl.findAll();
                model.addAttribute("userFlight",userFlight);
            }
        }
        return "flight-info";
    }

    @GetMapping("/updateFlight/{ufid}/{seat}")
    public String cancelFlight(@PathVariable(value="ufid") Long ufid,
                               @PathVariable(value = "seat") Integer seat,
                               Model model){

        Optional<UserFlight> userFlight = userFlightServiceImpl.findById(ufid);
        if(userFlight.isPresent()) {
            UserFlight uf = userFlight.get();
            uf.setSeat(seat);
            System.out.println("ufSeat:"+uf.getSeat());
            userFlightServiceImpl.save(uf);
            model.addAttribute("userFlight",uf);
            model.addAttribute("flight",uf.getFlight());
        }
        return "flight-info";
    }
}
