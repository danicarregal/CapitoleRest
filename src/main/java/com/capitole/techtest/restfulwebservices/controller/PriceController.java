package com.capitole.techtest.restfulwebservices.controller;

import com.capitole.techtest.restfulwebservices.exception.PriceNotFoundException;
import com.capitole.techtest.restfulwebservices.service.PriceDaoService;
import com.capitole.techtest.restfulwebservices.service.dto.PriceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

	private final PriceDaoService service;

	public PriceController(PriceDaoService service) {
		this.service = service;
	}

	
	@GetMapping("/price")
	public PriceDto retrievePrice(@RequestParam int brandId, @RequestParam int productId, @RequestParam LocalDateTime date) {

		PriceDto price = service.findByBrandAndProductAndDate(brandId,productId,date);
		
		if(price ==null)
			throw new PriceNotFoundException("No price found for (" + brandId + "," + productId + "," + date + ")");

		return price;
	}
}
