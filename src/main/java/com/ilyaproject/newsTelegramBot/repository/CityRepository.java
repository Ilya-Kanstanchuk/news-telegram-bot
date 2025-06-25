package com.ilyaproject.newsTelegramBot.repository;

import com.ilyaproject.newsTelegramBot.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City getByName(String name);
}
