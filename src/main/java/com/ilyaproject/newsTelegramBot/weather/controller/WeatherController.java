package com.ilyaproject.newsTelegramBot.weather.controller;

import com.ilyaproject.newsTelegramBot.weather.DTO.Weather;
import com.ilyaproject.newsTelegramBot.weather.service.WeatherService;
import com.ilyaproject.newsTelegramBot.weather.service.WeatherToMessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService service;
    private final WeatherToMessageConverter weatherToMessage;
    public String getForecast(Double latitude, Double longitude, String cityName){
        Weather weather = service.getForecast(latitude, longitude, cityName);
        return weatherToMessage.convert(weather);
    }
}
