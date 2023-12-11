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

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class DeleteDataController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @GetMapping("/{table}/delete")
    public String deleteData(@PathVariable String table, Model model) {
        String link;
        if ("user".equals(table)) {
            List<User> dataList = userRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "choseIdToDelete";
        } else if ("flightBooking".equals(table)) {
            List<FlightBooking> dataList = flightRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "choseIdToDeleteFB";
        }
        else if ("hotelBooking".equals(table)) {
            List<HotelBooking> dataList = hotelRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "choseIdToDeleteHB";
        }else {
            model.addAttribute("error" , "Firstly select table to work with");
            link = "errorPage";
        }
        return link;
    }

    @PostMapping("/{table}/delete")
    public String goToDelete(@PathVariable String table,@RequestParam long id, Model model){
        String link = "";
        try {
            if ("user".equals(table)) {
                List<User> dataList = userRepo.findAll();
                model.addAttribute("dataList", dataList);
                if (userRepo.findById(id).isPresent()) {
                    userRepo.deleteById(id);
                    link = "redirect:/{table}/delete";
                }
                else{
                    link = "choseIdToDelete";
                    model.addAttribute("error", "Can't find data by ID.");
                }
            } else if ("flightBooking".equals(table)) {
                List<FlightBooking> dataList = flightRepo.findAll();
                model.addAttribute("dataList", dataList);
                if (flightRepo.findById(id).isPresent()) {
                    flightRepo.deleteById(id);
                    link = "redirect:/{table}/delete";
                }
                else{
                    link = "choseIdToDeleteFB";
                    model.addAttribute("error", "Can't find data by ID.");
                }
            } else if ("hotelBooking".equals(table)) {
                List<HotelBooking> dataList = hotelRepo.findAll();
                model.addAttribute("dataList", dataList);
                if (hotelRepo.findById(id).isPresent()) {
                    hotelRepo.deleteById(id);
                    link = "redirect:/{table}/delete";
                }
                else{
                    link = "choseIdToDeleteHB";
                    model.addAttribute("error", "Can't find data by ID.");
                }
           }
            return link;
        }
        catch (Exception e){
            model.addAttribute("error", "Can't delete this user.");
            return "errorPage";
        }
    }
    @GetMapping("/{table}/delete/{id}")
    public String deleteByIdData(@PathVariable String table,@PathVariable Long id, Model model){
        try {
            if ("user".equals(table)) {
                userRepo.deleteById(id);
            } else if ("flightBooking".equals(table)) {
                flightRepo.deleteById(id);
            } else {
                hotelRepo.deleteById(id);
            }
            return "redirect:/{table}/uploadPage";
        }
        catch (Exception e){
            model.addAttribute("error", "Can't delete this user.");
            return "errorPage";
        }
    }
}
