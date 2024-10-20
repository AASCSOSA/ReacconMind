package com.reacconmind.reacconmind.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.reacconmind.reacconmind.repository.NotificationStrategy;
import com.reacconmind.reacconmind.service.ModerationNotificationStrategy;

@Configuration
public class ModerationConfig {
    @Bean
    public NotificationStrategy moderationNotificationStrategy() {
        return new ModerationNotificationStrategy();
    }
}