package com.reacconmind.reacconmind.controller;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reacconmind.reacconmind.dto.MessageDTO;
import com.reacconmind.reacconmind.model.Message;
import com.reacconmind.reacconmind.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@Tag(name = "Message")
@RequestMapping("ReacconMind/messages")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Operation(summary = "Get all messages", description = "Retrieve all messages.")
    @ApiResponse(responseCode = "200", description = "List of messages obtained successfully.")
    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAllMessages() {
        List<MessageDTO> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @Operation(summary = "Get message by ID", description = "Retrieve a message by its ID.")
    @ApiResponse(responseCode = "200", description = "Message retrieved successfully.")
    @ApiResponse(responseCode = "404", description = "Message not found.")
    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable int id) {
        Optional<MessageDTO> messageDTO = messageService.getMessageById(id);
        return messageDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new message", description = "Create a new message.")
    @ApiResponse(responseCode = "201", description = "Message created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody Message message) {
        MessageDTO createdMessage = messageService.saveMessage(message);
        return ResponseEntity.status(201).body(createdMessage);
    }

    @Operation(summary = "Delete a message", description = "Delete a message by its ID.")
    @ApiResponse(responseCode = "204", description = "Message deleted successfully.")
    @ApiResponse(responseCode = "404", description = "Message not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Retrieve messages by specific date", description = "Get all messages sent on a specific date (format: yyyy-MM-dd).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Messages retrieved successfully.", 
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageDTO.class)))),
        @ApiResponse(responseCode = "400", description = "Invalid date. Cannot query for a future date."),
        @ApiResponse(responseCode = "404", description = "No messages found for the specified date.")
    })
    @GetMapping("/by-date")
    public ResponseEntity<?> getMessagesByDate(
        @Parameter(description = "Date to filter messages (format: yyyy-MM-dd)", required = true)
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        try {
            // Intentamos obtener los mensajes
            List<MessageDTO> messages = messageService.getMessagesByDate(date);
            
            if (messages.isEmpty()) {
                // Si no se encuentran mensajes, retornamos un 404
                return ResponseEntity.status(404).body("No messages found for the specified date.");
            }

            // Si se encuentran mensajes, devolvemos un 200 con los mensajes
            return ResponseEntity.ok(messages);
        } catch (IllegalArgumentException e) {
            // En caso de fecha futura, devolvemos un 400 Bad Request con un mensaje
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    
    
}
