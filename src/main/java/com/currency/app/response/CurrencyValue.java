package com.currency.app.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyValue implements Comparable<CurrencyValue> {
	
	private LocalDate date;
	
	private BigDecimal value;

	public CurrencyValue(LocalDate date, BigDecimal value) {
		this.date = date;
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public int compareTo(CurrencyValue other) {
		return date.compareTo(other.date);		
	}
}
