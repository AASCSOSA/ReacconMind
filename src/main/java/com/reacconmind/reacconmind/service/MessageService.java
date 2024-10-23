package com.reacconmind.reacconmind.service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reacconmind.reacconmind.dto.MessageDTO;
import com.reacconmind.reacconmind.model.Message;
import com.reacconmind.reacconmind.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    private List<MessageDTO> convertToDTOList(List<Message> messages) {
        return messages.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MessageDTO saveMessage(Message message) {
        if (message.getIdSender() == message.getIdAddressee()) {
            throw new IllegalArgumentException("The user cannot send a message to himself.");
        }

        int maxId = messageRepository.findAll().stream()
                .mapToInt(Message::getIdMessage)
                .max()
                .orElse(1); 
        message.setIdMessage(maxId + 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = LocalDateTime.now().format(formatter);
        message.setShippingDate(formattedDate);
        
        Message savedMessage = messageRepository.save(message);
        return convertToDTO(savedMessage);
    }

    public List<MessageDTO> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return convertToDTOList(messages);
    }

    public Optional<MessageDTO> getMessageById(int id) {
        return messageRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }

    public List<MessageDTO> getMessagesByDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date. Cannot query for a future date.");
        }
        String datePrefix = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Message> messages = messageRepository.findByShippingDateStartingWith(datePrefix);

        if (messages.isEmpty()) {
            throw new IllegalArgumentException("No messages found for the specified date.");
        }
        return convertToDTOList(messages);
    }

    private MessageDTO convertToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setIdMessage(message.getIdMessage());
        messageDTO.setIdSender(message.getIdSender());
        messageDTO.setIdAddressee(message.getIdAddressee());
        messageDTO.setContent(message.getContent());
        messageDTO.setMultimedia(message.getMultimedia());
        messageDTO.setShippingDate(LocalDateTime.now());
        return messageDTO;
    }
}
