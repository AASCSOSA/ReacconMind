package com.reacconmind.reacconmind.repository;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reacconmind.reacconmind.model.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, Integer> {
    List<Message> findByShippingDateStartingWith(String datePrefix);
}
