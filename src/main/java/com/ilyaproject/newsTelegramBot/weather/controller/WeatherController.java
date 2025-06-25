package com.ilyaproject.newsTelegramBot.weather.controller;

import com.ilyaproject.newsTelegramBot.weather.DTO.Weather;
import com.ilyaproject.newsTelegramBot.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
@CrossOrigin
public class WeatherController {
    private final WeatherService service;
    @GetMapping("/test/forecast")
    public ResponseEntity<?> getForecast(){
        Weather weather = service.getForecast(53.9, 27.56667);
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }
}
