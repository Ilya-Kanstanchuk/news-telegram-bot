package com.ilyaproject.newsTelegramBot.news.DTO;

import com.ilyaproject.newsTelegramBot.utils.DataToObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsRootResponse{
    private String status;
    private int totalResults;
    private List<Article> articles;
}