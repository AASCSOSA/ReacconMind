package com.ReacconMind.ReacconMind.controller;

import com.ReacconMind.ReacconMind.model.Image;
import com.ReacconMind.ReacconMind.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ReacconMind/images")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ImageController {

    @Autowired
    private ImageService service;

    @Operation(summary = "Get all images")
    @ApiResponse(responseCode = "200", description = "Found Images", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class))})
    @GetMapping
    public List<Image> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get an image by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid image ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)})
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Image image = service.getByIdImage(id);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Upload an image", description = "Uploads an image file to Firebase Storage")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Image uploaded successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Image upload failed", content = @Content)
    })
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String url = service.uploadImage(file.getBytes(), file.getOriginalFilename());
            return new ResponseEntity<>("Image uploaded: " + url, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Image upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an image")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Image image, @PathVariable Integer id) {
        Image existingImage = service.getByIdImage(id);
        if (existingImage != null) {
            image.setIdImage(existingImage.getIdImage());
            service.save(image);
            return new ResponseEntity<>("Image updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete an image by its ID")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (service.getByIdImage(id) != null) {
            service.delete(id);
            return new ResponseEntity<>("Image deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
    }
}
