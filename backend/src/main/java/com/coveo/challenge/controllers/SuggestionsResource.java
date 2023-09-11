package com.coveo.challenge.controllers;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coveo.challenge.City;
import com.coveo.challenge.dao.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.MethodNotAllowedException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SuggestionsResource
{
	@Autowired
	private CsvParser csvParser;

	@CrossOrigin(origins = "*")
	@RequestMapping("/suggestions")
	public String suggestions(@RequestParam String q,
							  @RequestParam(defaultValue = "45.9778182", required = false) Double latitude,
							  @RequestParam(defaultValue = "-77.8968753", required = false) Double longitude,
							  @RequestParam(required = false) Integer page)
			throws Throwable {
		System.out.println(new Date() + " --- Entering suggestions endpoint parameters are: q=" + q + ", latitude="
				+ String.valueOf(latitude) + ", longitude=" + String.valueOf(longitude));

		Map<String, Object> results = new HashMap<>();
		try {
			//Reading of file can be part of a util static function which will help reduce the memory footprint
			//Also if this csv is static, it's best to load it during startup and not everytime the API is invoked
			ClassLoader classLoader = getClass().getClassLoader();
			List<City> cities = new ArrayList<>(List.copyOf(csvParser.readCities(classLoader.getResourceAsStream("data/cities_canada-usa.tsv"))
					.values()));
			cities.removeIf(c -> !c.name.contains(q));
			//We have default values for latitude and longitude, null check is not needed here.
			if (latitude != null) {
				cities.removeIf(city -> Math.abs(city.latitude - latitude) > 10);
			}
			if (longitude != null) {
				cities.removeIf(city -> Math.abs(city.longitude - longitude) > 20);
			}
			if (page != null) {
				results.put("page", page);
				results.put("totalNumberOfPages", cities.size() % 5 == 0 ? cities.size() / 5 : (cities.size() / 5) + 1);
				if (page < (int) results.get("totalNumberOfPages")) {
					cities = cities.subList((page * 5), (page * 5 + 5) >= cities.size() ? cities.size() : page * 5 + 5);
				} else {
					cities = List.of();
				}
			}
			results.put("cities", cities);
			return new ObjectMapper().writeValueAsString(results);
		} catch (InvalidParameterException | MethodNotAllowedException e) {
			// will never happen
			throw new Error();
		} catch (JsonProcessingException e) {
			// will also never happen
			throw new Throwable();
		}
		//Catching and throwing exceptions without a clear reason is unnecessary.
		// We should remove the catch blocks or add appropriate error.
	}
}
