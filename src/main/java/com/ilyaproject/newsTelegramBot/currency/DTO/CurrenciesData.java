package com.ilyaproject.newsTelegramBot.currency.DTO;

import com.ilyaproject.newsTelegramBot.utils.DataToObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrenciesData extends DataToObject {
    public Currency PLN;
    public Currency EUR;
    public Currency BYN;
}
