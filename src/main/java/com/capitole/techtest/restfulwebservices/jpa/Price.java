package com.capitole.techtest.restfulwebservices.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity(name = "PRICES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Price {
	
	@Id
	@GeneratedValue
	@Column(name = "PRICE_LIST")
	private Integer priceList;

	@Column(name = "START_DATE")
	private LocalDateTime startDate;

	@Column(name = "END_DATE")
	private LocalDateTime endDate;

	@Column(name = "BRAND_ID")
	private Integer brandId;

	@Column(name = "PRODUCT_ID")
	private Integer productId;

	@Column(name = "PRIORITY")
	private Integer priority;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "CURR")
	private Currency currency;
}


