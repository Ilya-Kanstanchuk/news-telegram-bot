package com.ilyaproject.newsTelegramBot.currency.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    public String code;
    public Double value;
}
