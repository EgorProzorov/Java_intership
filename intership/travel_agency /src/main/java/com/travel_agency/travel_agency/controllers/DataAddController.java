package com.travel_agency.travel_agency.controllers;

import com.travel_agency.travel_agency.models.FlightBooking;
import com.travel_agency.travel_agency.models.HotelBooking;
import com.travel_agency.travel_agency.models.User;
import com.travel_agency.travel_agency.repos.FlightRepo;
import com.travel_agency.travel_agency.repos.HotelRepo;
import com.travel_agency.travel_agency.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DataAddController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @GetMapping("/{table}/add")
    public String addData(@PathVariable String table, Model model){
        String link;
        if ("user".equals(table)) {
            link = "addPage";
        }
        else if("flightBooking".equals(table)){
            link = "addPageFB";
        }
        else if("hotelBooking".equals(table)){
            link = "addPageHB";
        }
        else {
            model.addAttribute("error" , "Firstly select table to work with");
            link = "errorPage";
        }
        return link;
    }
    @PostMapping("/{table}/add")
    public String addNewData(@PathVariable String table, @ModelAttribute User data, @ModelAttribute FlightBooking dataFB, @ModelAttribute HotelBooking dataHB, Model model){
        try {
            String link;
            if ("user".equals(table)) {
                userRepo.save(data);
                link = "redirect:/{table}/uploadPage";
            } else if ("flightBooking".equals(table)) {
                flightRepo.save(dataFB);
                link = "redirect:/{table}/uploadPage";
            } else if ("hotelBooking".equals(table)) {
                hotelRepo.save(dataHB);
                link = "redirect:/{table}/uploadPage";
            } else {
                link = "errorPage";
            }
            return link;
        }
        catch(Exception e){
            model.addAttribute("error", "Cant find user with this id");
            return "errorPage";
        }
    }
}
