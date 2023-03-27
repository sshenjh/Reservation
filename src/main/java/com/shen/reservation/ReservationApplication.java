package com.shen.reservation;

import com.shen.reservation.model.Flight;
import com.shen.reservation.model.User;
import com.shen.reservation.repository.FlightRepository;
import com.shen.reservation.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
        System.out.println("Running Reservation App");
    }


    @Bean
    CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder, FlightRepository flights){
        return args -> {
            users.save(new User("user",encoder.encode("password"),
                    "shenjames0625@gmail.com","ROLE_USER"));
            users.save(new User("admin",encoder.encode("password"),
                    "admin@gmail.com","ROLE_USER,ROLE_ADMIN"));

            flights.save(new Flight("AA852",10,"Los Angeles","Phoenix","American Airlines"));
            flights.save(new Flight("DL420",10,"Los Angeles","Boston","Delta Airlines"));
            flights.save(new Flight("CI5107",10,"Los Angeles","San Francisco","China Airlines"));
        };
    }

}
