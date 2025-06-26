package com.ilyaproject.newsTelegramBot.currency.service;

import com.ilyaproject.newsTelegramBot.currency.DTO.CurrenciesData;
import com.ilyaproject.newsTelegramBot.currency.DTO.CurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final WebClient client;
    @Value("${currency.api_key}")
    private String apiKey;

    private CurrenciesData getCurrency(String convertToCurrencies){
        CurrencyResponse response;
        try {
            response = client.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("api.currencyapi.com")
                            .path("/v3/latest")
                            .queryParam("apikey", apiKey)
                            .queryParam("currencies", convertToCurrencies)
                            .build())
                    .retrieve()
                    .bodyToMono(CurrencyResponse.class)
                    .block();
        }catch (Exception e){
            throw new RuntimeException("Failed to get currency " + e);
        }
        if (response == null){
            throw new RuntimeException("Currency data is null ");
        }
        return response.getData();
    }

    public CurrenciesData getPLN_EUR_BYN_To_USD(){
        return getCurrency("PLN,EUR,BYN");
    }
}
