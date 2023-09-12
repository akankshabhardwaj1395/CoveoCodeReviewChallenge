package com.coveo.challenge.models;

/**
 * Class representing a city and it's attributes.
 */
//TODO: this class can have getters and setters along with lombok annotations
public class City
{
    /**
     * The unique id of the city.
     */
    public Number id;

    /**
     * The city name.
     */
    public String name;

    /**
     * The city name in ascii (for foreign regions).
     */
    public String ascii;

    /**
     * The alternative name.
     */
    public String alt_name;

    /**
     * The latitude.
     */
    public Float latitude;

    /**
     * The longitude.
     */
    public Float longitude;

    /**
     * The country code.
     */
    public String country;

    /**
     * The state or province.
     */
    public String admin1;

    /**
     * The population.
     */
    public Number population;

    /**
     * The city elevation.
     */
    public Number elevation;

    /**
     * The timezone.
     */
    public String tz;

    /**
     * The date the data was updated.
     */
    public String modified_at;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAscii() {
        return ascii;
    }

    public void setAscii(String ascii) {
        this.ascii = ascii;
    }

    public String getAlt_name() {
        return alt_name;
    }

    public void setAlt_name(String alt_name) {
        this.alt_name = alt_name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdmin1() {
        return admin1;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    public Number getPopulation() {
        return population;
    }

    public void setPopulation(Number population) {
        this.population = population;
    }

    public Number getElevation() {
        return elevation;
    }

    public void setElevation(Number elevation) {
        this.elevation = elevation;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public String getFeat_class() {
        return feat_class;
    }

    public void setFeat_class(String feat_class) {
        this.feat_class = feat_class;
    }

    public String getFeat_code() {
        return feat_code;
    }

    public void setFeat_code(String feat_code) {
        this.feat_code = feat_code;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getDem() {
        return dem;
    }

    public void setDem(String dem) {
        this.dem = dem;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getAdmin3() {
        return admin3;
    }

    public void setAdmin3(String admin3) {
        this.admin3 = admin3;
    }

    public String getAdmin4() {
        return admin4;
    }

    public void setAdmin4(String admin4) {
        this.admin4 = admin4;
    }

    /**
     * The geoname class. (see: http://www.geonames.org/export/codes.html )
     */
    public String feat_class;

    /**
     * The geoname code. (see: http://www.geonames.org/export/codes.html )
     */
    public String feat_code;

    // Un-documented attributes
    public String cc2;
    public String dem;
    public String admin2;
    public String admin3;
    public String admin4;
}
