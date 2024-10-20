package com.reacconmind.reacconmind.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reacconmind.reacconmind.repository.NotificationRepository;
import com.reacconmind.reacconmind.repository.NotificationStrategy;

@Component
public class AlertNotificationStrategy implements NotificationStrategy {
    @Autowired
    private NotificationRepository repository;

    @Override
    public void send(Notification notification) {
        String customMessage = notification.getIdUser().getName() +
                ", your post cannot be uploaded because it violates the rules of coexistence.";
        notification.setContent(customMessage);

        repository.save(notification);
        System.out.println("Notificación saved in database " + notification.getContent());

    }

}
