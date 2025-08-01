package com.onlineLearningPlatform.repository;

import com.onlineLearningPlatform.model.Notification;
import com.onlineLearningPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRespository extends JpaRepository<Notification,Long> {

    User findByUser(User user);
}
