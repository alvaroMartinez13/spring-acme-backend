package com.project.spring_acme_backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.spring_acme_backend.Entities.Notifications;
import com.project.spring_acme_backend.Entities.Users;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {

    List<Notifications> findByReceiverOrderByCreatedAtDesc(Users receiver);

    Long countByReceiverAndIsReadFalse(Users receiver);

}
