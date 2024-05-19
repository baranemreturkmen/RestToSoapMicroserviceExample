package com.javaet.service.impl;

import com.javaet.service.CountryService;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;

import javax.xml.soap.*;

@Service
public class CountryServiceImpl implements CountryService {

    @Override
    public Response getCountry(String countryName) {
        /*GetCountryResponse getCountryResponse = null;
        try {
            URL url = new URL("http://localhost:9394/ws/countries.wsdl");
            QName qname = new QName("http://localhost:9394/", "CountryEndpoint");

            javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
            //TODO: Hangi class'a istek atacağımı bilmiyorsam yada göremiyorsam o zaman ne olacak? Dinamik olarak gitmeyi deneyelim.
            //Soap microservice projesine dependency eklememe rağmen import edemedim country endpoint'i. Compile time'da değil, runtime'da kızıyor. Bundan dolayı yukarıda ki todo'yu her türlü uygulamak zorundayım.
            //CountryEndpoint countryEndpointResponse = service.getPort(CountryEndpoint.class);
            GetCountryRequest getCountryRequest = new GetCountryRequest();
            getCountryRequest.setName(countryName);

            getCountryResponse = countryEndpointResponse.getCountry(getCountryRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCountryResponse;*/
        try {
            // SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Create SOAP Message
            String soapEndpointUrl = "http://localhost:9394/ws";
            String soapAction = "http://www.baeldung.com/springsoap/gen/getCountry";

            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(countryName, soapAction), soapEndpointUrl);

            // Process the SOAP Response
            SOAPBody responseBody = soapResponse.getSOAPBody();
            System.out.println("SOAP Response:");
            soapResponse.writeTo(System.out);

            // Assuming the response has a structure you can parse
            String responseString = responseBody.getElementsByTagNameNS("http://www.baeldung.com/springsoap/gen", "country").item(0).getTextContent();

            soapConnection.close();
            return Response.ok(responseString).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calling SOAP service").build();
        }
    }

    private SOAPMessage createSOAPRequest(String param, String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "tns";
        String myNamespaceURI = "http://www.baeldung.com/springsoap/gen";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        /*
        Constructed SOAP Request Message:
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="http://www.baeldung.com/springsoap/gen">
           <soapenv:Header/>
           <soapenv:Body>
              <tns:getCountryRequest>
                 <tns:name>param</tns:name>
              </tns:getCountryRequest>
           </soapenv:Body>
        </soapenv:Envelope>
        */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getCountryRequest", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("name", myNamespace);
        soapBodyElem1.addTextNode(param);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

}
