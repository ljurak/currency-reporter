package com.currency.app.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class CurrencyForm {
	
	@NotNull(message = "{currency.form.required}")
	private Currency baseCurrency;
	
	@NotNull(message = "{currency.form.required}")
	private Currency targetCurrency;
	
	@NotNull(message = "{currency.form.required}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "{currency.form.pastDate}")
	private LocalDate startDate;
	
	@NotNull(message = "{currency.form.required}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "{currency.form.pastDate}")
	private LocalDate endDate;

	public Currency getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Currency getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(Currency targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
