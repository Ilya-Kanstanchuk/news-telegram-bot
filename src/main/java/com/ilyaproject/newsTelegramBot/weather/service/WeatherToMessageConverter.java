package com.ilyaproject.newsTelegramBot.weather.service;

import com.ilyaproject.newsTelegramBot.utils.DataToMessageConverter;
import com.ilyaproject.newsTelegramBot.utils.DataToObject;
import com.ilyaproject.newsTelegramBot.weather.DTO.Weather;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeatherToMessageConverter implements DataToMessageConverter<Weather> {
    @Override
    public String convert(Weather weather) {
        Long minTemp = Math.round(weather.getDaily().getTemperature2mMin().get(0));
        String strMinTemp;
        if (minTemp > 0){
            strMinTemp = "+ " + minTemp;
        }else
        {
            strMinTemp = minTemp.toString();
        }
        Long maxTemp = Math.round(weather.getDaily().getTemperature2mMax().get(0));
        String strMaxTemp;
        if (maxTemp > 0){
            strMaxTemp = "+ " + maxTemp;
        }else
        {
            strMaxTemp = maxTemp.toString();
        }
        LocalDate currentDate = weather.getDaily().getTime().get(0);
        int year = currentDate.getYear();
        String month = currentDate.getMonth().toString();
        int day = currentDate.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append("Today is ")
                .append(day)
                .append(" ")
                .append(month)
                .append(" ")
                .append(year)
                .append("\n")
                .append("That's your forecast for ")
                .append(weather.getCityName())
                .append(":")
                .append("\n")
                .append("Minimal temperature is going to be ")
                .append(strMinTemp)
                .append("\n")
                .append("Maximum temperature is going to be ")
                .append(strMaxTemp);
        return sb.toString();
    }
}
