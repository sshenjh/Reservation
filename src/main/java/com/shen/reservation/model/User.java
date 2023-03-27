package com.shen.reservation.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User{
    @GeneratedValue
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String roles;

    public User(String username,String password,String email, String roles){
        this.username=username;
        this.password=password;
        this.email=email;
        this.roles=roles;
        this.userFlight=new ArrayList<>();
//        this.flights = new ArrayList<>();
    }


//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(name= "user_flight",
//            joinColumns =@JoinColumn(name= "user_id", referencedColumnName = "id"),
//            inverseJoinColumns =@JoinColumn(name= "flight_id", referencedColumnName = "id")
//    )
//    private List<Flight> flights = new ArrayList<>();
//
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFlight> userFlight = new ArrayList<>();

}
