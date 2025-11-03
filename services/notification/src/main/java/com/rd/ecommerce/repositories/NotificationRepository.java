package com.rd.ecommerce.repositories;

import com.rd.ecommerce.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
