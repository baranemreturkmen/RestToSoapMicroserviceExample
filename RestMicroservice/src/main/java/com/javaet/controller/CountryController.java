package com.javaet.controller;

import com.javaet.service.CountryService;
import jakarta.ws.rs.core.Response;
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

    @GetMapping("/getCountry/{countryName}")
    public Response getCountryByCountryName(@PathVariable("countryName") String countryName){
        return (Response) countryService.getCountry(countryName).getEntity();
        //Şuan burada hata var! Dönüş tipiyle ilgili. TODO: Dto dön!
    }

}
