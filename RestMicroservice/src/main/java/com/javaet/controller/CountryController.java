package com.javaet.controller;

import com.javaet.response.ErrorResponse;
import com.javaet.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("/getCountry/{countryName}")
    public ResponseEntity<?> getCountryByCountryName(@PathVariable("countryName") String countryName){
        var countryResponse = countryService.getCountry(countryName);
        if(Objects.isNull(countryResponse)){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Soap service not found ");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

}
