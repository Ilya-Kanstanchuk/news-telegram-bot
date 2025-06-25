package com.ilyaproject.newsTelegramBot.city.controller;

import com.ilyaproject.newsTelegramBot.city.service.CityService;
import com.ilyaproject.newsTelegramBot.model.City;
import com.ilyaproject.newsTelegramBot.weather.service.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CityInitialization {
    private final CityService service;
    public City cityStart(String name, Double latitude, Double longitude){
        return service.handleUserAddCity(name, latitude, longitude);
    }
}
