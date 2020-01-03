package com.currency.app.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.currency.app.form.Currency;
import com.currency.app.form.CurrencyForm;
import com.currency.app.response.CurrencyValue;
import com.currency.app.service.CurrencyReportService;

@Controller
public class CurrencyReportController {
	
	private CurrencyReportService currencyReportService;
	
	@Autowired
	public CurrencyReportController(CurrencyReportService currencyReportService) {
		this.currencyReportService = currencyReportService;
	}
	
	@ModelAttribute("allCurrencies")
	public List<Currency> populateCurrencies() {
		return Arrays.asList(Currency.values());
	}
	
	@GetMapping({ "/", "/currencyreport" })
	public String showReportPage(Model model) {
		model.addAttribute(new CurrencyForm());
		return "currencyreport";
	}
	
	@PostMapping("/currencyreport")
	public String showCurrencyReport(@ModelAttribute @Valid CurrencyForm currencyForm, BindingResult result, RedirectAttributes model) {
		validateStartAndEndDate(currencyForm, result);
		
		if (result.hasErrors()) {
			return "currencyreport";
		}
		
		List<CurrencyValue> currencyReport = currencyReportService.generateCurrencyReport(currencyForm);
		model.addFlashAttribute("currencyReport", currencyReport);
		model.addFlashAttribute("baseCurrency", currencyForm.getBaseCurrency());
		model.addFlashAttribute("targetCurrency", currencyForm.getTargetCurrency());
		
		return "redirect:/currencyreport";
	}
	
	private void validateStartAndEndDate(CurrencyForm currencyForm, BindingResult result) {
		LocalDate startDate = currencyForm.getStartDate();
		LocalDate endDate = currencyForm.getEndDate();
		
		if (startDate != null && endDate != null) {
			if (startDate.isAfter(endDate)) {
				result.reject("currency.form.date.mismatch");
			}
		}
	}
}
