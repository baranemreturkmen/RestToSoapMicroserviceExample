package com.javaet.service;

import com.baeldung.springsoap.gen.GetCountryResponse;

public interface CountryService {

    GetCountryResponse getCountry(String countryName);

}
