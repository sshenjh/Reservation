package com.shen.reservation.repository;

import com.shen.reservation.model.Flight;
import com.shen.reservation.model.UserFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFlightRepository extends JpaRepository<UserFlight, Long> {
    @Query("select u from UserFlight u where u.flight.id = ?1")
    List<UserFlight> findByFlight_Id(Long id);

    @Query("""
            select (count(u) > 0) from UserFlight u inner join u.flight.userFlight userFlight
            where u.flight.id = ?1 and userFlight.seat = ?2""")
    boolean existsByFlight_IdAndFlight_UserFlight_Seat(Long id, Integer seat);



    @Query("select (count(u) > 0) from UserFlight u where u.user.username = ?1 and u.flight.id = ?2")
    boolean existsByUser_UsernameAndFlight_Id(String username, Long id);





}
