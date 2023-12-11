package com.travel_agency.travel_agency.controllers;

import com.travel_agency.travel_agency.models.User;
import com.travel_agency.travel_agency.repos.UserRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/")
    public String Home(){
        return "main";
    }

    @PostMapping("/")
    public String blogPostAdd(@RequestParam String table, Model model){
        String link;
            model.addAttribute("table", table);
            link = "redirect:/" + table;
            //return link;
        return link;
    }
    @GetMapping("/{table}")
    public String SelectTable(@PathVariable String table, Model model){
        model.addAttribute("table", table);
        return "main";
    }

    @PostMapping("/{table}")
    public String blogPostAddTable(@RequestParam String table, Model model){
        String link;
            model.addAttribute("table", table);
            link = "redirect:/" + table;
            //return link;
        return link;
    }
}