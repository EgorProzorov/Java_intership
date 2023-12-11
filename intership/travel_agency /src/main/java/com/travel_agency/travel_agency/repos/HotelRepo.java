package com.travel_agency.travel_agency.repos;

import com.travel_agency.travel_agency.models.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<HotelBooking, Long> {

}
