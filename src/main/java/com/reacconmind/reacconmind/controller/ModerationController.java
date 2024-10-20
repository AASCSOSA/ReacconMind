package com.reacconmind.reacconmind.controller;

import com.reacconmind.reacconmind.dto.ModerationResponseDTO;
import com.reacconmind.reacconmind.model.Moderation;
import com.reacconmind.reacconmind.enums.ModerationTypeEnum;
import com.reacconmind.reacconmind.service.ModerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moderations")
public class ModerationController {
    @Autowired
    private ModerationService moderationService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping
    public List<ModerationResponseDTO> getAllModerations() {
        return moderationService.getAllModerations().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/publication/{publicationId}")
    public List<ModerationResponseDTO> getModerationsByPublication(@PathVariable int publicationId) {
        return moderationService.getModerationsByPublication(publicationId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<ModerationResponseDTO> getModerationsByUser(@PathVariable int userId) {
        return moderationService.getModerationsByUser(userId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModerationResponseDTO> getModerationById(@PathVariable int id) {
        return moderationService.getModerationById(id)
            .map(moderation -> ResponseEntity.ok(convertToDTO(moderation)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/moderate/text")
    public ResponseEntity<ModerationResponseDTO> moderateText(
            @RequestParam int publicationId,
            @RequestParam String content,
            @RequestParam int userId) {
        Moderation result = moderationService.moderateText(publicationId, content, userId);
        return ResponseEntity.ok(convertToDTO(result));
    }

    @PostMapping("/moderate/image")
    public ResponseEntity<ModerationResponseDTO> moderateImage(
            @RequestParam int publicationId,
            @RequestParam MultipartFile image,
            @RequestParam int userId) {
        Moderation result = moderationService.moderateImage(publicationId, image, userId);
        return ResponseEntity.ok(convertToDTO(result));
    }

    private ModerationResponseDTO convertToDTO(Moderation moderation) {
        return new ModerationResponseDTO(
            moderation.getIdPublication(),
            moderation.getModerationType().toString(),
            getModerationMessage(moderation.getModerationType()),
            moderation.getModerationDate().format(formatter)
        );
    }

    private String getModerationMessage(ModerationTypeEnum moderationType) {
        switch (moderationType) {
            case APPROVED:
                return "El contenido cumple con las normas de la comunidad.";
            case REJECTED:
                return "El contenido no cumple con las normas de la comunidad y ha sido rechazado.";
            case PENDING:
                return "El contenido está pendiente de revisión.";
            default:
                return "Estado de moderación desconocido.";
        }
    }
}