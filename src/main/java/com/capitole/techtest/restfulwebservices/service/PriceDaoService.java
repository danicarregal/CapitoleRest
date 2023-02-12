package com.capitole.techtest.restfulwebservices.service;

import com.capitole.techtest.restfulwebservices.jpa.Price;
import com.capitole.techtest.restfulwebservices.jpa.PriceRepository;
import com.capitole.techtest.restfulwebservices.service.dto.PriceDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class PriceDaoService {

	private final PriceRepository repository;
	private final ModelMapper modelMapper;

	public PriceDaoService(PriceRepository repository, ModelMapper modelMapper){
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	public PriceDto findByBrandAndProductAndDate(int brandId, int productId, LocalDateTime date){
		Price resultPrice = null;
		List<Price> pricesByBrandAndProduct = repository.findByBrandIdAndProductId(brandId, productId);
		Predicate<? super Price> predicate = price -> !date.isBefore(price.getStartDate()) && !date.isAfter(price.getEndDate());
		List<Price> resultPrices = pricesByBrandAndProduct.stream().filter(predicate).collect(Collectors.toList());
		if(!resultPrices.isEmpty()){

			Collections.sort(resultPrices, Comparator.comparing(Price::getPriority).reversed());
			resultPrice = resultPrices.get(0);
		}
		return convertToDto(resultPrice);
	}

	private PriceDto convertToDto(Price price) {

		return (price!=null) ? modelMapper.map(price, PriceDto.class) : null;
	}
}
