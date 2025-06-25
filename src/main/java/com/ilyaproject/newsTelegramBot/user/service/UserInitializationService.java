package com.ilyaproject.newsTelegramBot.user.service;

import com.ilyaproject.newsTelegramBot.model.City;
import com.ilyaproject.newsTelegramBot.model.Status;
import com.ilyaproject.newsTelegramBot.model.User;
import com.ilyaproject.newsTelegramBot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserInitializationService {
    private final UserRepository repository;
    public void handleUserPressStart(Long chatId, String name, City city){
        try {
            User user = repository.getByChatId(chatId);
            if (user == null){
                saveUser(chatId, name, city);
            }else {
                updateUser(chatId, name, city, user);
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to save user information " + e);
        }
    }

    private void saveUser(Long chatId, String name, City city){
        User user = User
                .builder()
                .chatId(chatId)
                .name(name)
                .city(city)
                .status(Status.ACTIVE)
                .build();
        city.getUsers().add(user);
        repository.save(user);
    }
    private void updateUser(Long chatId, String name, City city, User user){
        user.setChatId(chatId);
        user.setName(name);
        if (!user.getCity().getName().equals(city.getName())){
            City oldCity = user.getCity();
            oldCity.getUsers().remove(user);
            user.setCity(city);
            city.getUsers().add(user);
        }
        repository.save(user);
    }
}
