package com.technokratos.aiocars.emailnotificationsservice.service.impl;

import com.technokratos.aiocars.emailnotificationsservice.messaging.NotificationMessageDto;
import com.technokratos.aiocars.emailnotificationsservice.service.EmailService;
import freemarker.template.Configuration;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private final Configuration freemarkerConfiguration;

    @Override
    public void sendNotification(NotificationMessageDto emailNotificationMessage) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(
                    String.format("New advertisement on AIOCars - %s %s",
                            emailNotificationMessage.getCar().getBrand(), emailNotificationMessage.getCar().getName()));
            helper.setTo(emailNotificationMessage.getTarget());
            String emailContent = getEmailContent(emailNotificationMessage);
            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
            log.info("Email was sent to {}", emailNotificationMessage.getTarget());
        } catch (Exception e){
            log.error("Email sending error", e);
        }
    }

    @SneakyThrows
    private String getEmailContent(NotificationMessageDto emailNotificationMessage){
        @Cleanup StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", emailNotificationMessage.getUser());
        model.put("advertisements", emailNotificationMessage.getAdvertisements());
        model.put("car", emailNotificationMessage.getCar());
        freemarkerConfiguration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
