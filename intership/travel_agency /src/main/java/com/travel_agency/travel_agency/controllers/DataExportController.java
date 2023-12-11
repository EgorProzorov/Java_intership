package com.travel_agency.travel_agency.controllers;

import com.opencsv.CSVWriter;
import com.travel_agency.travel_agency.models.FlightBooking;
import com.travel_agency.travel_agency.models.HotelBooking;
import com.travel_agency.travel_agency.models.User;
import com.travel_agency.travel_agency.services.CSVDataService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/{table}/download")
public class DataExportController {
    @Autowired
    private CSVDataService dataService;


    @GetMapping("/csv")
    public void downloadCSV(@PathVariable String table,HttpServletResponse response, Model model) throws IOException {
            response.setContentType("text/csv");
            String headerValue = "attachment; filename=" + table + ".csv";
            response.setHeader("Content-Disposition", headerValue);
            CSVWriter csvWriter = new CSVWriter(response.getWriter());
            if ("user".equals(table)) {
                List<User> data = dataService.getAllData();
                String[] header = {"Id", "First Name", "Last Name", "Email", "Password"};
                csvWriter.writeNext(header);
                for (User entity : data) {
                    String[] row = {
                            String.valueOf(entity.getId()),
                            entity.getFirst_Name(),
                            entity.getSecond_Name(),
                            entity.getEmail(),
                            entity.getPass()
                    };


                    csvWriter.writeNext(row);
                }
            } else if ("flightBooking".equals(table)) {
                List<FlightBooking> data = dataService.getAllDataFB();
                String[] header = {"Booking id", "User id", "Flight id", "Booking date"};
                csvWriter.writeNext(header);
                for (FlightBooking entity : data) {
                    String[] row = {String.valueOf(entity.getBooking_id()), String.valueOf(entity.getUser_id()), String.valueOf(entity.getFlight_id()), entity.getBooking_date_and_time()};
                    csvWriter.writeNext(row);
                }
            } else if ("hotelBooking".equals(table)) {
                List<HotelBooking> data = dataService.getAllDataHB();
                String[] header = {"Booking id", "User id", "Hotel id", "Start date", "End date"};
                csvWriter.writeNext(header);
                for (HotelBooking entity : data) {
                    String[] row = {String.valueOf(entity.getBooking_id()), String.valueOf(entity.getUser_id()), String.valueOf(entity.getHotel_id()), entity.getStart_date(), entity.getEnd_date()};
                    csvWriter.writeNext(row);
                }
            }
            else{
                model.addAttribute("error", "Chose table");
            }
        csvWriter.close();
    }
}
