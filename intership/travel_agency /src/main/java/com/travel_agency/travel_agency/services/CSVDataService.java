package com.travel_agency.travel_agency.services;

import com.opencsv.CSVReader;
import com.travel_agency.travel_agency.models.FlightBooking;
import com.travel_agency.travel_agency.models.HotelBooking;
import com.travel_agency.travel_agency.models.User;
import com.travel_agency.travel_agency.repos.FlightRepo;
import com.travel_agency.travel_agency.repos.HotelRepo;
import com.travel_agency.travel_agency.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVDataService {
    private final UserRepo dataRepository;

    private final FlightRepo flightRepo;


    private final HotelRepo hotelRepo;

    @Autowired
    public CSVDataService(
            UserRepo dataRepository,
            FlightRepo flightRepo,
            HotelRepo hotelRepo
    ) {
        this.dataRepository = dataRepository;
        this.flightRepo = flightRepo;
        this.hotelRepo = hotelRepo;
    }


    @Transactional
    public boolean processCSV(String table, MultipartFile file) throws IOException {
        boolean feedback = true;
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
            if ("user".equals(table)) {
                while ((nextLine = reader.readNext()) != null) {
                    System.out.println(Arrays.toString(nextLine));
                    if (nextLine.length >= 5) {
                        User csvData = new User();
                        csvData.setFirst_Name(nextLine[1]);
                        csvData.setSecond_Name(nextLine[2]);
                        csvData.setEmail(nextLine[3]);
                        csvData.setPass(nextLine[4]);
                        dataRepository.save(csvData);
                    } else {
                        feedback = false;
                        System.err.println("Incorrects data input1");
                    }
                }
            } else if ("flightBooking".equals(table)) {
                while ((nextLine = reader.readNext()) != null) {
                    System.out.println(Arrays.toString(nextLine));
                    if (nextLine.length >= 4) {
                        FlightBooking csvData = new FlightBooking();
                        csvData.setUser_id(Long.parseLong(nextLine[1]));
                        csvData.setFlight_id(Long.parseLong(nextLine[2]));
                        csvData.setBooking_date_and_time(nextLine[3]);
                        flightRepo.save(csvData);
                    } else {
                        feedback = false;
                        System.err.println("Incorrects data input2");
                    }
                }
            } else if ("hotelBooking".equals(table)) {
                while ((nextLine = reader.readNext()) != null) {
                    System.out.println(Arrays.toString(nextLine));
                    if (nextLine.length >= 5) {
                        HotelBooking csvData = new HotelBooking();
                        csvData.setUser_id(Long.parseLong(nextLine[1]));
                        csvData.setHotel_id(Long.parseLong(nextLine[2]));
                        csvData.setStart_date(nextLine[3]);
                        csvData.setEnd_date(nextLine[4]);
                        hotelRepo.save(csvData);
                    } else {
                        feedback = false;
                        System.err.println("Incorrects data input3");
                    }
                }
            }
        } catch (Exception e) {
            feedback = false;
            System.err.println("Fatal error");
        }
        return feedback;
    }

    public List<User> getAllData() {
        return dataRepository.findAll();
    }

    public List<FlightBooking> getAllDataFB() {
        return flightRepo.findAll();
    }

    public List<HotelBooking> getAllDataHB() {
        return hotelRepo.findAll();
    }
}
