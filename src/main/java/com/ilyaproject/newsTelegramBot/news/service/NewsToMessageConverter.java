package com.ilyaproject.newsTelegramBot.news.service;

import com.ilyaproject.newsTelegramBot.news.DTO.NewsData;
import com.ilyaproject.newsTelegramBot.news.DTO.NewsDataWrapper;
import com.ilyaproject.newsTelegramBot.utils.DataToMessageConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsToMessageConverter implements DataToMessageConverter<NewsDataWrapper> {
    @Override
    public String convert(NewsDataWrapper data) {
        List<NewsData> list1 = data.getFirstListWithData();
        List<NewsData> list2 = data.getSecondListWithData();
        StringBuilder sb = new StringBuilder();
        sb.append("Your daily morning news ")
                .append("\n")
                .append("\n");
        for (NewsData article: list1){
            sb.append(article.getTitle())
                    .append("\n")
                    .append(article.getUrl())
                    .append("\n")
                    .append("\n")
                    .append("\n");

        }
        for (NewsData article: list2){
            sb.append(article.getTitle())
                    .append("\n")
                    .append(article.getUrl())
                    .append("\n")
                    .append("\n")
                    .append("\n");
        }
        return sb.toString();
    }
}
