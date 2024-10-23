package com.reacconmind.reacconmind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.reacconmind.reacconmind.model.Notification;
import com.reacconmind.reacconmind.repository.NotificationRepository;
import com.reacconmind.reacconmind.repository.NotificationStrategy;

@Component
public class ModerationNotificationStrategy implements NotificationStrategy {
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void send(Notification notification) {
        // Formatear el mensaje de moderación
        String moderationMessage = String.format(
            "Tu contenido ha sido revisado: %s", 
            notification.getContent()
        );
        
        // Actualizar el contenido de la notificación
        notification.setContent(moderationMessage);
        notification.setState(Notification.State.Unread);
        notification.setTypeNotification(Notification.TypeNotification.Moderation);
        
        // Guardar la notificación en la base de datos
        notificationRepository.save(notification);
    }

    public void sendRejectionNotification(int userId, int publicationId) {
        Notification notification = new Notification();
        notification.setIdUser(userId);
        notification.setTypeNotification(Notification.TypeNotification.Moderation);
        notification.setContent("Tu publicación #" + publicationId + 
            " ha sido rechazada por incumplir las normas de la comunidad.");
        notification.setState(Notification.State.Unread);
        
        send(notification);
    }

    public void sendApprovalNotification(int userId, int publicationId) {
        Notification notification = new Notification();
        notification.setIdUser(userId);
        notification.setTypeNotification(Notification.TypeNotification.Moderation);
        notification.setContent("Tu publicación #" + publicationId + 
            " ha sido aprobada y ya está visible para la comunidad.");
        notification.setState(Notification.State.Unread);
        
        send(notification);
    }
}