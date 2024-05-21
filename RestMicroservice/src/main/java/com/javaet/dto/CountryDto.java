package com.javaet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {
    private String countryName;
    private String population;
    private String capital;
    private String currency;
}
