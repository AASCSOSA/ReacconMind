package com.reacconmind.reacconmind.service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
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

        // Obtener el máximo ID y asignar un nuevo ID
        int maxId = messageRepository.findAll().stream()
                .mapToInt(Message::getIdMessage)
                .max()
                .orElse(0);  // Cambié de 1 a 0 para evitar duplicados
        message.setIdMessage(maxId + 1);

        // Asignar la fecha actual al mensaje
        // message.setShippingDate(LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = LocalDateTime.now().format(formatter);
        message.setShippingDate(formattedDate);
        // Guardar el mensaje en la base de datos
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
        // Validación: no permitir fechas futuras
        LocalDate today = LocalDate.now();
        if (date.isAfter(today)) {
            throw new IllegalArgumentException("Invalid date. Cannot query for a future date.");
        }

        // Convertir la fecha a String con formato yyyy-MM-dd
        String datePrefix = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Llamar al repository para obtener los mensajes que empiecen con ese prefijo
        List<Message> messages = messageRepository.findByShippingDateStartingWith(datePrefix);
        return convertToDTOList(messages);
    }

    private MessageDTO convertToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setIdMessage(message.getIdMessage());
        messageDTO.setIdSender(message.getIdSender());
        messageDTO.setIdAddressee(message.getIdAddressee());
        messageDTO.setContent(message.getContent());
        messageDTO.setMultimedia(message.getMultimedia());
        // Respetar la fecha de envío real en lugar de asignar LocalDateTime.now()
        // messageDTO.setShippingDate(message.getShippingDate());
        messageDTO.setShippingDate(LocalDateTime.now());
        return messageDTO;
    }
}
