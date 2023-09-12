package com.coveo.challenge.controllers;

import com.coveo.challenge.models.CityResponse;
import com.coveo.challenge.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionsResource
{
	@Autowired
	CityService cityService;

	//Documentation should be added about each param
	//Instead of q, we should use query
	@CrossOrigin(origins = "*")
	@RequestMapping("/suggestions")
	public ResponseEntity<CityResponse> suggestions(@RequestParam String q,
													@RequestParam(defaultValue = "45.9778182", required = false) Double latitude,
													@RequestParam(defaultValue = "-77.8968753", required = false) Double longitude,
													@RequestParam(required = false) Integer page) {
		return ResponseEntity.ok(cityService.getCities(longitude, latitude, q, page));
	}
}
