package com.technokratos.telegramnotificationservice.telegram;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {

    Optional<TelegramUser> findByUsername(String username);

    Optional<TelegramUser> findByChatId(Long chatId);
}
