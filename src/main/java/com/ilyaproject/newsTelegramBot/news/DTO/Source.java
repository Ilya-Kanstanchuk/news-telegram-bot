package com.ilyaproject.newsTelegramBot.news.DTO;

import com.ilyaproject.newsTelegramBot.utils.DataToObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Source{
    private String id;
    private String name;
}