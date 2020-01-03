package com.currency.app.service;

import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currency.app.form.Currency;
import com.currency.app.form.CurrencyForm;
import com.currency.app.response.CurrencyReportResponse;
import com.currency.app.response.CurrencyValue;

@Service
public class CurrencyReportServiceImpl implements CurrencyReportService {
	
	private static final String API_URL_TEMPLATE = 
			"https://api.exchangeratesapi.io/history?start_at=%s&end_at=%s&base=%s&symbols=%s";
	
	private RestTemplate restTemplate;
	
	@Autowired
	public CurrencyReportServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public List<CurrencyValue> generateCurrencyReport(CurrencyForm currencyForm) {
		Currency baseCurrency = currencyForm.getBaseCurrency();
		Currency targetCurrency = currencyForm.getTargetCurrency();
		
		String url = String.format(
				API_URL_TEMPLATE, 
				currencyForm.getStartDate(), 
				currencyForm.getEndDate(), 
				baseCurrency, 
				targetCurrency);
		
		CurrencyReportResponse response = restTemplate.getForObject(url, CurrencyReportResponse.class);
		List<CurrencyValue> currencyValues = response.getRates()
				.entrySet()
				.stream()
				.map(entry -> new CurrencyValue(entry.getKey(), entry.getValue().get(targetCurrency).setScale(4, RoundingMode.HALF_UP)))
				.sorted()
				.collect(Collectors.toList());
		
		return currencyValues;
	}
}
