package com.ReacconMind.ReacconMind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReacconMind.ReacconMind.model.Notification;
import com.ReacconMind.ReacconMind.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationRepository repository;

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

    public void maskAsRead(Notification notification) {
        notification.setState( );
    }

}
