package com.ilyaproject.newsTelegramBot.weather.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ilyaproject.newsTelegramBot.utils.DataToObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Daily extends DataToObject {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> time;
    @JsonProperty("temperature_2m_min")
    private List<Double> temperature2mMin;

    @JsonProperty("temperature_2m_max")
    private List<Double> temperature2mMax;

}