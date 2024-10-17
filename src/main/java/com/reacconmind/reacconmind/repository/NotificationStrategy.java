package com.reacconmind.reacconmind.repository;

import com.reacconmind.reacconmind.model.Notification;

public interface NotificationStrategy {
    void send(Notification notification);
}
