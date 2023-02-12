package com.capitole.techtest.restfulwebservices.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PriceDto {

	@JsonProperty(value = "price_list")
	private Integer priceList;

	@JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
	@JsonProperty(value = "start_date")
	private LocalDateTime startDate;

	@JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
	@JsonProperty(value = "end_date")
	private LocalDateTime endDate;

	@JsonProperty(value = "brand_id")
	private Integer brandId;

	@JsonProperty(value = "product_id")
	private Integer productId;

	@JsonIgnore
	@JsonProperty(value = "priority")
	private Integer priority;

	@JsonProperty(value = "price")
	private BigDecimal price;

	@JsonProperty(value = "currency")
	private Currency currency;
}