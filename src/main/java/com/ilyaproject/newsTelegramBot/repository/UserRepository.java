package com.ilyaproject.newsTelegramBot.repository;

import com.ilyaproject.newsTelegramBot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getByChatId(Long chatId);
}
