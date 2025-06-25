package com.ilyaproject.newsTelegramBot.city.service;

import com.ilyaproject.newsTelegramBot.model.City;
import com.ilyaproject.newsTelegramBot.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;
    public City handleUserAddCity(String name, Double latitude, Double longitude){
        City city = repository.getByName(name);
        if (city == null){
            city = creteCity(name, latitude, longitude);
        }else{
            updateCity(name, latitude, longitude, city);
        }
        return city;
    }
    private City creteCity(String name, Double latitude, Double longitude){
        City city = City
                .builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .users(new HashSet<>())
                .build();
        return repository.save(city);
    }
    private City updateCity(String name, Double latitude, Double longitude, City city){
        city.setName(name);
        city.setLatitude(latitude);
        city.setLongitude(longitude);
        return repository.save(city);
    }
}
