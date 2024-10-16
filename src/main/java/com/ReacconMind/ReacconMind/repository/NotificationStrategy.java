package com.ReacconMind.ReacconMind.repository;

import com.ReacconMind.ReacconMind.model.Notification;

public interface NotificationStrategy {
    void send(Notification notification);
}
