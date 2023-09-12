package com.coveo.challenge.models;

import java.util.List;
import java.util.Objects;

/**
 * @author Akanksha Bhardwaj
 **/

public class CityResponse {
    private Integer page;
    private Integer totalNumberOfPages;
    private List<City> cities;

    public CityResponse(Integer page, Integer totalNumberOfPages, List<City> cities) {
        this.page = page;
        this.totalNumberOfPages = totalNumberOfPages;
        this.cities = cities;
    }

    public CityResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page == null) {
            throw new IllegalArgumentException("page is invalid. Expected class is Integer");
        }
        this.page = page;
    }

    public Integer getTotalNumberOfPages() {
        return Objects.requireNonNullElse(totalNumberOfPages, 0);
    }

    public void setTotalNumberOfPages(Integer totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
