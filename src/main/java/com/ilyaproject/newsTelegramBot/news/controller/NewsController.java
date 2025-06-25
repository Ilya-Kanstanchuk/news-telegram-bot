package com.ilyaproject.newsTelegramBot.news.controller;

import com.ilyaproject.newsTelegramBot.news.DTO.NewsDataWrapper;
import com.ilyaproject.newsTelegramBot.news.service.NewsService;
import com.ilyaproject.newsTelegramBot.news.service.NewsToMessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class NewsController {
    private final NewsService service;
    private final NewsToMessageConverter converter;

    public String getNews(){
        NewsDataWrapper wrapper = service.getNewsWrapper();
        return converter.convert(wrapper);
    }
}
