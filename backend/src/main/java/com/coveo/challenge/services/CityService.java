package com.coveo.challenge.services;

import com.coveo.challenge.dao.CsvParser;
import com.coveo.challenge.models.City;
import com.coveo.challenge.models.CityResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Akanksha Bhardwaj
 **/
@Component
public class CityService {

    public static final int PAGE_SIZE = 5; //helps in readability as well as reduce memory footprint since its static

    @Autowired
    CsvParser dao;//since only our service needs data, it makes sense to have it as a
    // dependency here and not at controller. Controller should have only the service layer

    /**
     * Add javadoc to help understand code better
     * Added the cityResponse , added the logic to filter out rather than negate and remove
     * since it is an expensive operation and unnecessary
     * <p>
     * \@param longitude
     * \@param latitude
     * \@param q
     * \@param pageNumber
     * \@return
     */
    public CityResponse getCities(Double longitude, Double latitude, String q,
                                  Integer pageNumber) {
        CityResponse cityResponse = new CityResponse();

        try {
            /**
             * loaded the class just to get the current path.
             */
            ClassLoader classLoader = getClass().getClassLoader();

            List<City> initialAllSetOfCities = new ArrayList<>(
                    List.copyOf(dao.readCities(classLoader.getResourceAsStream("data/cities_canada-usa.tsv"))
                            .values()));

            List<City> filteredCities = initialAllSetOfCities.stream()
                    .filter(city -> city.name.contains(q))
                    .filter(city -> Math.abs(city.latitude - latitude) <= 10)
                    .filter(city -> Math.abs(city.longitude - longitude) <= 20)
                    .collect(Collectors.toList());

            if (pageNumber != null) {
                //100 list, 5 page size, pages = 21 (101/5)
                cityResponse.setPage(pageNumber);
                cityResponse.setTotalNumberOfPages(getTotalNumberOfPages(filteredCities));
                if (pageNumber < (int) cityResponse.getTotalNumberOfPages()) {
                    filteredCities = filteredCities.subList((pageNumber * PAGE_SIZE),
                            getLastIndex(pageNumber, filteredCities));
                } else {
                    filteredCities = List.of();
                }
            }
            cityResponse.setCities(filteredCities);
        } catch (Exception exception) {
            System.out.println("Error while searching out the cities  " + exception.getMessage());
        }
        return cityResponse;
    }


    // lesser memory foot print since its static, better readibility
    private static int getLastIndex(Integer pageNumber, List<City> cities) {
        return (pageNumber * PAGE_SIZE + PAGE_SIZE) >= cities.size() ? cities.size()
                : pageNumber * PAGE_SIZE
                + PAGE_SIZE;
    }

    private static int getTotalNumberOfPages(List<City> cities) {
        return cities.size() % PAGE_SIZE
                == 0 ? cities.size() / PAGE_SIZE : (cities.size() / PAGE_SIZE) + 1;
    }

}
