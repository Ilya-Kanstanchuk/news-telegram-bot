package com.ilyaproject.newsTelegramBot.weather.DTO;


import com.ilyaproject.newsTelegramBot.utils.DataToObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Weather extends DataToObject {
    private Daily daily;
    private String cityName;
}