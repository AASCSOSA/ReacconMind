package com.reacconmind.reacconmind.controller;

import com.reacconmind.reacconmind.dto.ImageDTO;
import com.reacconmind.reacconmind.dto.PublicationDTO;
import com.reacconmind.reacconmind.model.Image;
import com.reacconmind.reacconmind.repository.ImageRepository;
import com.reacconmind.reacconmind.repository.PublicationRepository;
import com.reacconmind.reacconmind.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Operation(summary = "Upload an image file", description = "Upload an image file to Firebase and receive the image URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image uploaded successfully", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Image upload failed")
    })
    @PostMapping(value = "/upload-image", consumes = { "multipart/form-data" })
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Subir la imagen y obtener la URL
            String url = imageService.upload(file);

            // Crear una nueva instancia de Image
            Image image = new Image();
            image.setUrl(url);
            image.setUploadDate(new Timestamp(System.currentTimeMillis()));

            // Guardar la imagen en la base de datos
            imageRepository.save(image);

            // Crear un objeto JSON con la URL
            Map<String, String> response = new HashMap<>();
            response.put("url", url);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Image upload failed"));
        }
    }

    @Operation(summary = "Get all images", description = "Retrieve all images from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Images retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Failed to retrieve images")
    })
    @GetMapping("/images")
    public ResponseEntity<List<Image>> getAllImages() {
        try {
            List<Image> images = imageRepository.findAll();
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(summary = "Get image by ID", description = "Retrieve an image by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found"),
            @ApiResponse(responseCode = "500", description = "Failed to retrieve image")
    })
    @GetMapping("/images/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable int id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            return ResponseEntity.ok(imageOptional.get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @Operation(summary = "Delete image by ID", description = "Delete an image by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found"),
            @ApiResponse(responseCode = "500", description = "Failed to delete image")
    })
    @DeleteMapping("/images/{id}")
    public ResponseEntity<Map<String, String>> deleteImageById(@PathVariable int id) {
        try {
            if (imageRepository.existsById(id)) {
                imageRepository.deleteById(id);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Image deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(404).body(Map.of("error", "Image not found"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to delete image"));
        }
    }

    @Operation(summary = "Update image by ID", description = "Update an image's URL and other details by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image updated successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found"),
            @ApiResponse(responseCode = "500", description = "Failed to update image")
    })
    @PutMapping("/images/{id}")
    public ResponseEntity<Map<String, String>> updateImageById(@PathVariable int id, @RequestBody Image updatedImage) {
        try {
            Optional<Image> imageOptional = imageRepository.findById(id);
            if (imageOptional.isPresent()) {
                Image image = imageOptional.get();
                image.setUrl(updatedImage.getUrl()); // Actualizar la URL
                image.setThumbnail(updatedImage.getThumbnail()); // Actualizar la miniatura si es necesario
                image.setUploadDate(new Timestamp(System.currentTimeMillis())); // Actualizar la fecha de carga

                // Guardar los cambios en la base de datos
                imageRepository.save(image);

                Map<String, String> response = new HashMap<>();
                response.put("message", "Image updated successfully");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(404).body(Map.of("error", "Image not found"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to update image"));
        }
    }


    @GetMapping("/images/juan")
    public List<ImageDTO> findAllImages() {
        return imageRepository.findAllImages();
    }




}