package com.ReacconMind.ReacconMind.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ReacconMind.ReacconMind.repository.NotificationRepository;
import com.ReacconMind.ReacconMind.repository.NotificationStrategy;

@Component
public class FollowNotificationStrategy implements NotificationStrategy  {
    @Autowired
    private NotificationRepository repository;

    @Override
    public void send(Notification notification) {
        
        String customMessage = notification.getIdUser().getName() + " started following you.";
        notification.setContent(customMessage);  

        repository.save(notification);
        System.out.println("Notificación saved in database " + notification.getContent());
    }
}
