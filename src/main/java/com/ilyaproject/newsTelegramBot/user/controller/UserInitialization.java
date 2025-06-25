package com.ilyaproject.newsTelegramBot.user.controller;

import com.ilyaproject.newsTelegramBot.model.City;
import com.ilyaproject.newsTelegramBot.user.service.UserInitializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequiredArgsConstructor
public class UserInitialization {
    private final UserInitializationService service;
    public void userStart(Long chatId, String name, City city){
        try {
            service.handleUserPressStart(chatId, name, city);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
