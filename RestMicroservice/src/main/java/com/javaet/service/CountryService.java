package com.javaet.service;

import jakarta.ws.rs.core.Response;

public interface CountryService {

    Response getCountry(String countryName);

}
