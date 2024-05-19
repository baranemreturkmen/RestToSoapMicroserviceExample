package com.javaet.controller;

import com.baeldung.springsoap.gen.GetCountryResponse;
import com.javaet.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getCountry")
    public GetCountryResponse getCountryByCountryName(@PathVariable("countryName") String countryName){
        return countryService.getCountry(countryName);
    }

}
