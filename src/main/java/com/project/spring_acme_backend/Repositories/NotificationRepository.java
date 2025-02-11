package com.project.spring_acme_backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring_acme_backend.Entities.Notification;
import com.project.spring_acme_backend.Entities.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}
