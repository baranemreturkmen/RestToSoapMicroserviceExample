package com.javaet.service.impl;

import com.javaet.dto.CountryDto;
import com.javaet.service.CountryService;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;

@Service
public class CountryServiceImpl implements CountryService {

    @Override
    public CountryDto getCountry(String countryName) {
        Node countryNode = null;
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
            countryNode = (Node) responseBody.getElementsByTagNameNS("http://www.baeldung.com/springsoap/gen", "country").item(0);

            soapConnection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return fillCountryDto(countryNode);
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

    private CountryDto fillCountryDto(Node countryNode){
        if (countryNode != null) {
            NodeList countryDetails = countryNode.getChildNodes();
            String name = null, population = null, capital = null, currency = null;

            for (int i = 0; i < countryDetails.getLength(); i++) {
                Node detail = (Node) countryDetails.item(i);

                switch (detail.getLocalName()) {
                    case "name":
                        name = detail.getTextContent();
                        break;
                    case "population":
                        population = detail.getTextContent();
                        break;
                    case "capital":
                        capital = detail.getTextContent();
                        break;
                    case "currency":
                        currency = detail.getTextContent();
                        break;
                }
            }
        return CountryDto.builder().countryName(name).population(population).capital(capital).currency(currency).build();
    }
        return null;
    }

}
