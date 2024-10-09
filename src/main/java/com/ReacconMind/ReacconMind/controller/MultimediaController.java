package com.ReacconMind.ReacconMind.controller;

import java.util.List;

import com.ReacconMind.ReacconMind.model.Multimedia;
import com.ReacconMind.ReacconMind.service.MultimediaService;
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
@RequestMapping("multimedia")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
public class MultimediaController {

    @Autowired
    private MultimediaService service;

    @Operation(summary = "Get all multimedia entries")
    @ApiResponse(responseCode = "200", description = "Found Multimedia", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Multimedia.class))) })
    @GetMapping
    public List<Multimedia> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get a multimedia entry by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Multimedia found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Multimedia.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid multimedia ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Multimedia not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Multimedia multimedia = service.getById(id);
        return new ResponseEntity<>(multimedia, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Multimedia multimedia) {
        service.save(multimedia);
        return new ResponseEntity<>("Multimedia created", HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Multimedia multimedia, @PathVariable Integer id) {
        Multimedia existingMultimedia = service.getById(id);
        multimedia.setIdMultimedia(existingMultimedia.getIdMultimedia());
        service.save(multimedia);
        return new ResponseEntity<>("Multimedia updated", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>("Multimedia deleted", HttpStatus.OK);
    }
}
