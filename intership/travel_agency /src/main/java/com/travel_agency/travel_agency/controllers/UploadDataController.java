package com.travel_agency.travel_agency.controllers;


import com.travel_agency.travel_agency.models.FlightBooking;
import com.travel_agency.travel_agency.models.HotelBooking;
import com.travel_agency.travel_agency.models.User;
import com.travel_agency.travel_agency.repos.FlightRepo;
import com.travel_agency.travel_agency.repos.HotelRepo;
import com.travel_agency.travel_agency.repos.UserRepo;
import com.travel_agency.travel_agency.services.CSVDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class UploadDataController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FlightRepo flightRepo;

    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private CSVDataService dataService;

    @GetMapping("/{table}/uploadPage")
    public String uploadPage(@PathVariable String table, Model model) {
        String link;
        if ("user".equals(table)) {
            List<User> dataList = userRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "uploadPage";
        }
        else if("flightBooking".equals(table)){
            List<FlightBooking> dataList = flightRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "uploadPageFB";

        }
        else if("hotelBooking".equals(table)){
            List<HotelBooking> dataList = hotelRepo.findAll();
            model.addAttribute("dataList", dataList);
            link = "uploadPageHB";
        }
        else {
            model.addAttribute("error" , "Firstly select table to work with");
            link = "errorPage";
        }
        return link;
    }

    @PostMapping("/{table}/uploadPage")
    public String handleFileUpload(@PathVariable String table, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        try {
            boolean flag;
            flag = dataService.processCSV(table, file);
            if (flag == true) {
                return "redirect:/{table}/uploadPage";
            } else {
                model.addAttribute("error", "Incorrect data input. Data have to be separated by , and be in .csv format and has same count of columns as in web page");
                return "errorPage";
            }
        }
        catch (Exception e){
            model.addAttribute("error", "Incorrect data input. Data have to be separated by , and be in .csv format and has same count of columns as in web page");
            return "errorPage";
        }
    }
}
