package com.ilyaproject.newsTelegramBot.weather.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Weather {
    private Daily daily;
}