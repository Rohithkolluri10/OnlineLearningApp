package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRespository extends JpaRepository<Notification,Long> {
}
