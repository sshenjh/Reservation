package com.shen.reservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight{

    public Flight(String flightNumber,
                  Integer capacity,
                  String departure,
                  String arrival,
                  String airline){
        this.flightNumber=flightNumber;
        this.capacity=capacity;
        this.departure=departure;
        this.arrival=arrival;
        this.airline=airline;
        this.userFlight=new ArrayList<>();
//        this.users=new ArrayList<>();
    }

    @Id
    @GeneratedValue
    private Long id;

    private String flightNumber;

    private Integer capacity = 10;

    private String departure;

    private String arrival;

    private String airline ="United Airline";

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @JoinTable(name= "user_flight",
//            joinColumns = @JoinColumn(name= "flight_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id")
//    )
//    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<UserFlight> userFlight = new ArrayList<>();

}

