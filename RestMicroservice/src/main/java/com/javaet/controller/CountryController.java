package com.javaet.controller;

import com.javaet.dto.CountryDto;
import com.javaet.service.CountryService;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    //TODO: ResponsEntity ile kullan veya kendin yaz.
    @GetMapping("/getCountry/{countryName}")
    public CountryDto getCountryByCountryName(@PathVariable("countryName") String countryName){
        return countryService.getCountry(countryName);
    }

}
