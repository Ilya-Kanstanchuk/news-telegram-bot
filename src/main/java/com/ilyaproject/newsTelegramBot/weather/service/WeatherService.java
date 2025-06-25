package com.ilyaproject.newsTelegramBot.weather.service;

import com.ilyaproject.newsTelegramBot.weather.DTO.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WebClient client;
    public Weather getForecast(Double latitude, Double longitude){
        Weather weather;
        try {
            weather = client.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("api.open-meteo.com")
                            .path("/v1/forecast")
                            .queryParam("latitude", latitude)
                            .queryParam("longitude", longitude)
                            .queryParam("daily", "temperature_2m_max", "temperature_2m_min")
                            .queryParam("forecast_days", "1")
                            .build())
                    .retrieve()
                    .bodyToMono(Weather.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Failed request for weather API " + e);
        }
        if (weather == null){
            throw new RuntimeException("Weather object is null");
        }
        return weather;
    }
}
