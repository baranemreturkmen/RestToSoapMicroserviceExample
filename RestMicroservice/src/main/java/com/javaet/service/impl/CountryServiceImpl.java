package com.javaet.service.impl;

import com.baeldung.springsoap.gen.GetCountryRequest;
import com.baeldung.springsoap.gen.GetCountryResponse;
import com.javaet.controller.CountryEndpoint;
import com.javaet.service.CountryService;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.net.URL;

@Service
public class CountryServiceImpl implements CountryService {

    @Override
    public GetCountryResponse getCountry(String countryName) {
        GetCountryResponse getCountryResponse = null;
        try {
            URL url = new URL("http://localhost:9394/ws/countries.wsdl");
            QName qname = new QName("http://localhost:9394/", "CountryEndpoint");

            javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
            //TODO: Hangi class'a istek atacağımı bilmiyorsam yada göremiyorsam o zaman ne olacak? Dinamik olarak gitmeyi deneyelim.
            //Soap microservice projesine dependency eklememe rağmen import edemedim country endpoint'i. Compile time'da değil, runtime'da kızıyor. Bundan dolayı yukarıda ki todo'yu her türlü uygulamak zorundayım.
            CountryEndpoint countryEndpointResponse = service.getPort(CountryEndpoint.class);
            GetCountryRequest getCountryRequest = new GetCountryRequest();
            getCountryRequest.setName(countryName);

            getCountryResponse = countryEndpointResponse.getCountry(getCountryRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCountryResponse;
    }
}
