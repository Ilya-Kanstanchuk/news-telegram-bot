package com.ilyaproject.newsTelegramBot.currency.controller;

import com.ilyaproject.newsTelegramBot.currency.DTO.CurrenciesData;
import com.ilyaproject.newsTelegramBot.currency.service.CurrencyService;
import com.ilyaproject.newsTelegramBot.currency.service.CurrencyToMessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CurrencyController {
    private final CurrencyService service;
    private final CurrencyToMessageConverter converter;
    public String getCurrencies(){
        CurrenciesData data = service.getPLN_EUR_BYN_To_USD();
        return converter.convert(data);
    }
}
