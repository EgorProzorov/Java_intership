package com.travel_agency.travel_agency.models;


import jakarta.persistence.*;

@Entity
@Table(name="flight_booking")
public class FlightBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long booking_id;

    private Long user_id;
    private Long flight_id;
    private String booking_date_and_time;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user; // установка связи между таблицей User и FlightBooking - доразобраться что как работает


    public Long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public Long getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Long flight_id) {
        this.flight_id = flight_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getBooking_date_and_time() {
        return booking_date_and_time;
    }

    public void setBooking_date_and_time(String booking_date_and_time) {
        this.booking_date_and_time = booking_date_and_time;
    }

    public FlightBooking(Long flight_id, Long user_id, String booking_date_and_time) {
        this.flight_id = flight_id;
        this.user_id = user_id;
        this.booking_date_and_time = booking_date_and_time;
    }
    public FlightBooking() {

    }

}
