package com.reacconmind.reacconmind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reacconmind.reacconmind.model.Notification;
import com.reacconmind.reacconmind.model.Notification.TypeNotification;
import com.reacconmind.reacconmind.model.User;
import com.reacconmind.reacconmind.service.NotificationService;
import com.reacconmind.reacconmind.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Notification")
@RequestMapping("ReacconMind/notifications")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all Notifications", description = "Gets a list of all registered notifications.")
    @ApiResponse(responseCode = "200", description = "List of notifications obtained correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @GetMapping
    public List<Notification> getAll() {
        return notificationService.getAll();
    }

    @Operation(summary = "Get a Notifications", description = "Get a notification using your identifier.")
    @ApiResponse(responseCode = "200", description = "Notification obtained correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @GetMapping("/{idNotification}")
    public ResponseEntity<?> getNotificationById(@PathVariable Integer idNotification) {
        Notification notification = notificationService.getByIdNotification(idNotification);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);

    }

    @Operation(summary = "Mark a notification as read", description = "Mark a notification as read.")
    @ApiResponse(responseCode = "200", description = "Notification read correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @PutMapping("/{id}/read")
    public ResponseEntity<Void> maskAsRead(@PathVariable Integer id) {
        notificationService.maskAsRead(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get unread notifications", description = "Get all notifications that have not been read.")
    @ApiResponse(responseCode = "200", description = "List of unread notifications obtained correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })    
    @GetMapping("/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications() {
        List<Notification> unreadNotifications = notificationService.getUnreadNotifications();
        return ResponseEntity.ok(unreadNotifications); 
    }

    @Operation(summary = "Create notification of like", description = "Create and send a notification of like.")
    @ApiResponse(responseCode = "200", description = "Notification created correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @PostMapping("/like")
    public ResponseEntity<String> createAndSendLikeNotification(@RequestParam int idUser) {
        User user = userService.getByIdUser(idUser);

        Notification notification = new Notification();
        notification.setIdUser(user);
        notification.setTypeNotification(TypeNotification.Like);
        notificationService.sendNotification(notification);
        return ResponseEntity.ok("Notification of like sent successfully");
    }

    @Operation(summary = "Create message notification", description = "Create and send a message notification.")
    @ApiResponse(responseCode = "200", description = "Notification created correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @PostMapping("/message")
    public ResponseEntity<String> createAndSendMessageNotification(@RequestParam int idUser) {
        User user = userService.getByIdUser(idUser);

        Notification notification = new Notification();
        notification.setIdUser(user);
        notification.setTypeNotification(TypeNotification.Message);
        notificationService.sendNotification(notification);
        return ResponseEntity.ok("Message notification sent successfully");
    }

    @Operation(summary = "Create comment notification", description = "Create and send a comment notification.")
    @ApiResponse(responseCode = "200", description = "Notification created correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @PostMapping("/comment")
    public ResponseEntity<String> createAndSendCommentNotification(@RequestParam int idUser) {
        User user = userService.getByIdUser(idUser);

        Notification notification = new Notification();
        notification.setIdUser(user);
        notification.setTypeNotification(TypeNotification.Comment);
        notificationService.sendNotification(notification);
        return ResponseEntity.ok("Notice of comment sent successfully");
    }

    @Operation(summary = "Create follower notification", description = "Create and send a follower notification.")
    @ApiResponse(responseCode = "200", description = "Notification created correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Notification.class))) })
    @PostMapping("/follow")
    public ResponseEntity<String> createAndSendFollowNotification(@RequestParam int idUser) {
        User user = userService.getByIdUser(idUser);

        Notification notification = new Notification();
        notification.setIdUser(user);
        notification.setTypeNotification(TypeNotification.Follow);
        notificationService.sendNotification(notification);
        return ResponseEntity.ok("Follower notification sent successfully");
    }

}
