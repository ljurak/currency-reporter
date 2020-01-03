package com.currency.app.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import com.currency.app.form.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyReportResponse {
	
	@JsonProperty("start_at")
	private LocalDate startDate;
	
	@JsonProperty("end_at")
	private LocalDate endDate;
	
	private Currency base;
	
	private Map<LocalDate, Map<Currency, BigDecimal>> rates;

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Currency getBase() {
		return base;
	}

	public Map<LocalDate, Map<Currency, BigDecimal>> getRates() {
		return rates;
	}
}
