package com.ReacconMind.ReacconMind.controller;

import java.util.List;

import com.ReacconMind.ReacconMind.model.Image;
import com.ReacconMind.ReacconMind.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/ReacconMind/images")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
public class ImageController {

    @Autowired
    private ImageService service;

    @Operation(summary = "Get all images")
    @ApiResponse(responseCode = "200", description = "Found Images", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Image.class))) })
    @GetMapping
    public List<Image> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get an image by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Image.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid image ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Image image = service.getByIdImage(id);
        return new ResponseEntity<Image>(image, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Image image) {
        service.save(image);
        return new ResponseEntity<String>("Image created", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Image image, @PathVariable Integer id) {
        Image existingImage = service.getByIdImage(id);
        image.setIdImage(existingImage.getIdImage());
        service.save(image);
        return new ResponseEntity<String>("Image updated", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<String>("Image deleted", HttpStatus.OK);
    }
}
