package com.javaet.service;

import com.javaet.dto.CountryDto;
import jakarta.ws.rs.core.Response;

public interface CountryService {

    CountryDto getCountry(String countryName);

}
