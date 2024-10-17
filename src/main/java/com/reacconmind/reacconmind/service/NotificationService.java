package com.reacconmind.reacconmind.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.model.CommentNotificationStrategy;
import com.reacconmind.reacconmind.model.FollowNotificationStrategy;
import com.reacconmind.reacconmind.model.LikeNotificationStrategy;
import com.reacconmind.reacconmind.model.MessageNotificationStrategy;
import com.reacconmind.reacconmind.model.Notification;
import com.reacconmind.reacconmind.model.Notification.State;
import com.reacconmind.reacconmind.model.Notification.TypeNotification;
import com.reacconmind.reacconmind.repository.NotificationRepository;
import com.reacconmind.reacconmind.repository.NotificationStrategy;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationService {
    private final Map<TypeNotification, NotificationStrategy> strategies = new HashMap<>();
    @Autowired
    private NotificationRepository repository;

    public NotificationService(LikeNotificationStrategy likeNotificationStrategy,
            MessageNotificationStrategy messageNotificationStrategy,
            FollowNotificationStrategy followNotificationStrategy,
            CommentNotificationStrategy commentNotificationStrategy) {
        strategies.put(TypeNotification.Like, likeNotificationStrategy);
        strategies.put(TypeNotification.Message, messageNotificationStrategy);
        strategies.put(TypeNotification.Follow, followNotificationStrategy);
        strategies.put(TypeNotification.Comment, commentNotificationStrategy);
    }

    public List<Notification> getAll() {
        return repository.findAll();
    }

    public Notification getByIdNotification(Integer id_notification) {
        return repository.findById(id_notification).get();
    }

    public void save(Notification notification) {
        repository.save(notification);
    }

    public void delete(Integer id_notification) {
        repository.deleteById(id_notification);
    }

    public void maskAsRead(Integer idNotification) {
        Optional<Notification> notificacionOpt = repository.findById(idNotification);
        if (notificacionOpt.isPresent()) {
            Notification notificacion = notificacionOpt.get();
            notificacion.setState(State.Read);
            repository.save(notificacion);
        } else {
            throw new NoSuchElementException("Notificationo not found");
        }
    }

    public List<Notification> getUnreadNotifications(){
        return repository.findAll().stream()
               .filter(notification -> notification.getState() == State.Unread)
               .collect(Collectors.toList());
    }

    public void sendNotification(Notification notification) {
        NotificationStrategy strategy = strategies.get(notification.getTypeNotification());
        if (strategy != null) {
            strategy.send(notification);
            repository.save(notification);
        } else {
            throw new IllegalArgumentException("Unknow notification type");
        }

    }
}