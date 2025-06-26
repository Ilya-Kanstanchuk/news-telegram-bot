package com.ilyaproject.newsTelegramBot.currency.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ilyaproject.newsTelegramBot.utils.DataToObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {
    public CurrenciesData data;
}
