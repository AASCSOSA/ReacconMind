package com.reacconmind.reacconmind.service;

import org.springframework.stereotype.Component;
import com.reacconmind.reacconmind.model.Notification;
import com.reacconmind.reacconmind.repository.NotificationStrategy;

@Component
public class ModerationNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(Notification notification) {
        // Aquí implementamos la lógica específica para notificaciones de moderación
        String moderationMessage = String.format(
            "Tu contenido ha sido revisado: %s", 
            notification.getContent()
        );
        notification.setContent(moderationMessage);
        
        System.out.println("Enviando notificación de moderación: " + moderationMessage);
    }
}