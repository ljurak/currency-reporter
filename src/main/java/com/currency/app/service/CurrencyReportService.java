package com.currency.app.service;

import java.util.List;

import com.currency.app.form.CurrencyForm;
import com.currency.app.response.CurrencyValue;

public interface CurrencyReportService {
	List<CurrencyValue> generateCurrencyReport(CurrencyForm currencyForm);
}
