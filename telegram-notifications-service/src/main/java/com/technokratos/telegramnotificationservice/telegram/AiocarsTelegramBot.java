package com.technokratos.telegramnotificationservice.telegram;

import com.technokratos.telegramnotificationservice.messaging.NotificationMessageDto;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AiocarsTelegramBot extends TelegramLongPollingBot {

    private final TelegramUserRepository telegramUserRepository;

    @Value("${app.telegram.username}")
    private String botUsername;

    @Value("${app.telegram.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            Optional<TelegramUser> userOptional = telegramUserRepository.findByChatId(message.getChatId());
            if (userOptional.isEmpty()){
                telegramUserRepository.save(TelegramUser.builder()
                                .chatId(message.getChatId())
                                .username(message.getChat().getUserName())
                                .build());
                log.info("New telegram user registered: {}", message.getChat().getUserName());
            }
            sendMessage(message.getChatId(), "Now you can create a subscription to receive ads in telegram!");
        } catch (Exception e){
            log.error("Error receiving telegram updates", e);
        }
    }


    private void sendMessage(Long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Telegram api error", e);
        }
    };

    @SneakyThrows
    public void sendNotification(NotificationMessageDto notificationMessage){
        TelegramUser telegramUser = telegramUserRepository.findByUsername(notificationMessage.getTarget())
                .orElseThrow(TelegramUserNotFoundException::new);
        if (!notificationMessage.getAdvertisements().isEmpty() &&
                Objects.nonNull(notificationMessage.getAdvertisements().get(0).getImageUrl())){
            SendPhoto sendPhoto = new SendPhoto();
            @Cleanup InputStream imageInputStream =
                    new URL(notificationMessage.getAdvertisements().get(0).getImageUrl()).openStream();
            sendPhoto.setChatId(String.valueOf(telegramUser.getChatId()));
            sendPhoto.setPhoto(new InputFile(imageInputStream, String.valueOf(UUID.randomUUID())));
            execute(sendPhoto);
        }
        sendMessage(telegramUser.getChatId(), notificationMessage.toString());
    }
}
