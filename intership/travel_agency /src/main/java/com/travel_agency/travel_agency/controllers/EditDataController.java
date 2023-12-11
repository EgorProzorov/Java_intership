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
public class EditDataController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @GetMapping("/{table}/edit")
    public String choseField(@PathVariable String table, Model model){
        String link;
        if ("user".equals(table)) {
            List<User> dataList = userRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "choseIdToEdit";
        }
        else if("flightBooking".equals(table)){
            List<FlightBooking> dataList = flightRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "choseIdToEditFB";

        }
        else if("hotelBooking".equals(table)){
            List<HotelBooking> dataList = hotelRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "choseIdToEditHB";
        }
        else {
            model.addAttribute("error" , "Firstly select table to work with");
            link = "errorPage";
        }
        return link;
    }
    @PostMapping("/{table}/edit")
    public String goToEdit(@PathVariable String table, @RequestParam long id, Model model){
        String link;
        if ("user".equals(table)) {
            List<User> dataList = userRepo.findAll();
            model.addAttribute("dataList", dataList);
            if (userRepo.findById(id).isPresent()) {
                link = "redirect:/{table}/edit/" + id;
            } else {
                model.addAttribute("error", "Can't find data by ID.");
                link = "choseIdToEdit";
            }
        }
        else if("flightBooking".equals(table)){
            List<FlightBooking> dataList = flightRepo.findAll();
            model.addAttribute("dataList", dataList);
            if (flightRepo.findById(id).isPresent()) {
                link = "redirect:/{table}/edit/" + id;
            } else {
                model.addAttribute("error", "Can't find data by ID.");
                link = "choseIdToEditFB";
            }
        }
        else if("hotelBooking".equals(table)){
            List<HotelBooking> dataList = hotelRepo.findAll();
            model.addAttribute("dataList", dataList);
            if (hotelRepo.findById(id).isPresent()) {
                link = "redirect:/{table}/edit/" + id;
            } else {
                model.addAttribute("error", "Can't find data by ID.");
                link = "choseIdToEditHB";
            }
        }
        else {
            link = "errorPage";
        }
        return link;
    }

    @GetMapping("/{table}/edit/{id}")
    public String editData(@PathVariable String table, @PathVariable Long id, Model model) {
        String link;
        if ("user".equals(table)) {
            User data = userRepo.findById(id).orElse(null);
            model.addAttribute("data", data);
            link = "editDataPage";
        }
        else if("flightBooking".equals(table)){
            FlightBooking data = flightRepo.findById(id).orElse(null);
            model.addAttribute("data", data);
            link = "editDataPageFB";
        }
        else if("hotelBooking".equals(table)){
            HotelBooking data = hotelRepo.findById(id).orElse(null);
            model.addAttribute("data", data);
            link = "editDataPageHB";
        }
        else {
            link = "errorPage";
        }
        return link;
    }

    @PostMapping("/{table}/edit/{id}")
    public String SaveData(@PathVariable String table, @ModelAttribute User data, @ModelAttribute FlightBooking dataFB,@ModelAttribute HotelBooking dataHB, Model model){
        String link;
        try {
            if ("user".equals(table)) {
                userRepo.save(data);
                link = "redirect:/{table}/edit";
            } else if ("flightBooking".equals(table)) {
                flightRepo.save(dataFB);
                link = "redirect:/{table}/edit";
            } else if ("hotelBooking".equals(table)) {
                hotelRepo.save(dataHB);
                link = "redirect:/{table}/edit";
            } else {
                link = "errorPage";
            }
            return link;
        }
        catch (Exception e){
            model.addAttribute("error", "Cant find user with this ID");
            return "errorPage";
        }
    }
}
