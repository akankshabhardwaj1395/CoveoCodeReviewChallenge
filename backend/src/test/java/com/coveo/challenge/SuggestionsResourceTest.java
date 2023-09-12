/**
 * Copyright (c) 2011 - 2019, Coveo Solutions Inc.
 */
package com.coveo.challenge;

import com.coveo.challenge.controllers.SuggestionsResource;
import com.coveo.challenge.dao.CsvParser;
import com.coveo.challenge.models.City;
import com.coveo.challenge.models.CityResponse;
import com.coveo.challenge.services.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;


public class SuggestionsResourceTest
{
    SuggestionsResource suggestionsResource = new SuggestionsResource();

    @BeforeEach
    public void setUp() {
        CityService service = new CityService();
        ReflectionTestUtils.setField(service, "dao", new CsvParser(), CsvParser.class);
        ReflectionTestUtils.setField(suggestionsResource, "cityService", service, CityService.class);
    }
    @Test
    public void testSuggestionEndpoint() throws Throwable
    {
        // Create an empty CityResponse to compare against
        CityResponse expectedResponse = new CityResponse();
        expectedResponse.setPage(0);
        expectedResponse.setTotalNumberOfPages(1);
        List<City> listOfCities = new ArrayList<City>();
        City city = new City();
        city.setName("Bluewater");
        listOfCities.add(city);
        expectedResponse.setCities(listOfCities);
        // Call the suggestions method
        ResponseEntity<CityResponse> responseEntity = suggestionsResource.suggestions("Bluewater", 43.46679, -81.59977, 0);
        // Assert the HTTP status code
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the content of the CityResponse
        CityResponse actualResponse = responseEntity.getBody();
        Assertions.assertEquals(expectedResponse.getPage(), actualResponse.getPage());
        Assertions.assertEquals(expectedResponse.getTotalNumberOfPages(), actualResponse.getTotalNumberOfPages());
        Assertions.assertEquals(expectedResponse.getCities().get(0).getName(), actualResponse.getCities().get(0).getName());
    }

    @Test
    public void testSuggestionEndpoint2() throws Throwable
    {
        // Call the suggestions method
        ResponseEntity<CityResponse> responseEntity = suggestionsResource.suggestions("Qué", 45.9778182, -77.8968753, 0);
        String responseContent = new ObjectMapper().writeValueAsString(responseEntity.getBody());
        //System.out.println(responseContent);
        Assertions.assertTrue(responseContent.length() > 100);
    }

    @Test
    public void testSuggestionEndpoint3() throws Throwable
    {
        // Call the suggestions method
        ResponseEntity<CityResponse> responseEntity = suggestionsResource.suggestions("Qué", 45.9778182, -77.8968753, 0);
        String responseContent = new ObjectMapper().writeValueAsString(responseEntity.getBody());
        //System.out.println(responseContent);
        Assertions.assertEquals("{\"page\":0,\"totalNumberOfPages\":1,\"cities\":[{\"id\":6325494,\"name\":\"Québec\",\"ascii\":\"Quebec\",\"alt_name\":\"Bandaraya Quebec,Cathair Quebec,Cathair Québec,Ciudad ti Quebec,Ciutat de Quebec,Gorad Kvebek,Jiji la Quebec,Kebec Vile,Kebek,Kebeko,Kebeku,Kempek,Kota Quebec,Kuehbehk,Kvebek,Kvebeka,Kvebekas,Kwebek Shehiri,Kwébék Shehiri,Kébéc Vile,Lungsod ng Quebec,Lungsod ng Québec,Quebec,Quebec Ceety,Quebec City,Quebec Hiria,Quebec llaqta,Quebecborg,Quebecstad,Quebecum urbs,Québec,Québecborg,Tchubec,Thanh pho Quebec,Thành phố Québec,Vila de Quebec,Vila de Quebèc,Ville de Quebec,Ville de Québec,YQB,kbk,kh wibek,kiyupek nakaram,kui bei ke shi,kvebeka siti,kwebeg,kyubeka nagara,mdynt kybk,qwwybq syty,Κεμπέκ,Горад Квебек,Квебек,Куэбэк,קוויבק סיטי,مدينة كيبك,کبک,کیوبک شہر,क्यूबेक नगर,क्वेबेक सिटी,கியூபெக் நகரம்,ควิเบก,კვებეკი,ケベック・シティー,魁北克市,퀘벡\",\"latitude\":46.81228,\"longitude\":-71.21454,\"country\":\"CA\",\"admin1\":\"10\",\"population\":528595,\"elevation\":-1,\"tz\":\"America/Montreal\",\"modified_at\":\"2013-03-10\",\"feat_class\":\"P\",\"feat_code\":\"PPLA\",\"cc2\":\"\",\"dem\":\"54\",\"admin2\":\"\",\"admin3\":\"\",\"admin4\":\"\"}]}",
                responseContent);
    }

    @Test
    public void testSuggestionEndpoint4() throws Throwable
    {
        ResponseEntity<CityResponse> expectedResponseEntity = suggestionsResource.suggestions("Qué", 43.0, -23.2, null);
        ResponseEntity<CityResponse> actualResponseEntity = suggestionsResource.suggestions("Qué", 43.0, -23.2, null);
        String a = new ObjectMapper().writeValueAsString(expectedResponseEntity.getBody());
        String b = new ObjectMapper().writeValueAsString(actualResponseEntity.getBody());
        Assertions.assertEquals(b, a);
    }


}
