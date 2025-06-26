package com.ilyaproject.newsTelegramBot.currency.service;

import com.ilyaproject.newsTelegramBot.currency.DTO.CurrenciesData;
import com.ilyaproject.newsTelegramBot.currency.DTO.CurrencyResponse;
import com.ilyaproject.newsTelegramBot.utils.DataToMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class CurrencyToMessageConverter implements DataToMessageConverter<CurrenciesData> {
    @Override
    public String convert(CurrenciesData data) {
        StringBuilder sb = new StringBuilder();
        String PLNValue = String.format("%.3f", data.PLN.value);
        String EURValue = String.format("%.3f", data.EUR.value);
        String BYNValue = String.format("%.3f", data.BYN.value);
        sb.append("Dollar exchange rates for today")
                .append("\n")
                .append("\n")
                .append(data.PLN.code).append("  ").append(PLNValue)
                .append("\n")
                .append("\n")
                .append(data.EUR.code).append("  ").append(EURValue)
                .append("\n")
                .append("\n")
                .append(data.BYN.code).append("  ").append(BYNValue)
                .append("\n")
                .append("\n");
        return sb.toString();

    }
}
