package com.travel_agency.travel_agency.models;

import jakarta.persistence.*;

@Entity
@Table(name="hotel_booking")
public class HotelBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long booking_id;
    private Long user_id, hotel_id;
    private String start_date, end_date;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user; // установка связи между таблицей User и FlightBooking - доразобраться что как работает



    public Long getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }


    public HotelBooking(Long user_id, Long hotel_id, String start_date, String end_date, User user) {
        this.user_id = user_id;
        this.hotel_id = hotel_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.user = user;
    }

    public HotelBooking() {
    }
}
