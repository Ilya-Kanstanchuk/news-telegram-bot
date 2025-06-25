package com.ilyaproject.newsTelegramBot.news.service;

import com.ilyaproject.newsTelegramBot.news.DTO.NewsData;
import com.ilyaproject.newsTelegramBot.news.DTO.NewsDataWrapper;
import com.ilyaproject.newsTelegramBot.news.DTO.NewsRootResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final WebClient webClient;
    @Value("${news.api_key}")
    private String apiKey;
    private List<NewsData> getNews(String source){
        NewsRootResponse rootResponse;
        try {
            rootResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("newsapi.org")
                            .path("/v2/top-headlines")
                            .queryParam("sources", source)
                            .queryParam("pageSize", 3)
                            .queryParam("apiKey", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(NewsRootResponse.class)
                    .block();
        }catch (Exception e) {
            throw new RuntimeException("Failed request for news API " +  e);
        }
        if (rootResponse == null){
            throw new RuntimeException("Failed to get news data");
        }
        List<NewsData> articles = rootResponse.getArticles().stream()
                .map(article -> NewsData.builder()
                        .url(article.getUrl())
                        .title(article.getTitle())
                        .build())
                .toList();
        return articles;
    }
    private List<NewsData> getBBCNews(){
        return getNews("bbc-news");
    }
    private List<NewsData> getTCNews(){
        return getNews("techcrunch");
    }
    public NewsDataWrapper getNewsWrapper(){
        return NewsDataWrapper
                .builder()
                .firstListWithData(getBBCNews())
                .secondListWithData(getTCNews())
                .build();
    }
}
