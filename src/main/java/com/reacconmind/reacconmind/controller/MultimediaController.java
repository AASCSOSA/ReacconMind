package com.reacconmind.reacconmind.controller;

import com.reacconmind.reacconmind.model.Multimedia;
import com.reacconmind.reacconmind.service.MultimediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ReacconMind/multimedia")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
public class MultimediaController {

    @Autowired
    private MultimediaService service;

    @Operation(summary = "Get all multimedia entries")
    @ApiResponse(responseCode = "200", description = "Found Multimedia", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Multimedia.class))) })
    @GetMapping
    public ResponseEntity<List<Multimedia>> getAll() {
        List<Multimedia> multimediaList = service.getAll();
        return new ResponseEntity<>(multimediaList, HttpStatus.OK);
    }

    @Operation(summary = "Get a multimedia entry by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Multimedia found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Multimedia.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid multimedia ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Multimedia not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Multimedia> multimedia = service.getById(id);
        if (multimedia.isPresent()) {
            return new ResponseEntity<>(multimedia.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Multimedia not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new multimedia entry")
    @ApiResponse(responseCode = "201", description = "Multimedia created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Multimedia.class)) })
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Multimedia multimedia) {
        Multimedia savedMultimedia = service.save(multimedia);
        return new ResponseEntity<>("Multimedia created with ID: " + savedMultimedia.getIdMultimedia(), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing multimedia entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Multimedia updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid multimedia ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Multimedia not found", content = @Content) })
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Multimedia multimedia, @PathVariable Integer id) {
        Optional<Multimedia> existingMultimediaOpt = service.getById(id);
        if (existingMultimediaOpt.isPresent()) {
            multimedia.setIdMultimedia(existingMultimediaOpt.get().getIdMultimedia());
            service.save(multimedia);
            return new ResponseEntity<>("Multimedia updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Multimedia not found", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete a multimedia entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Multimedia deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Multimedia not found", content = @Content) })
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (service.delete(id)) {
            return new ResponseEntity<>("Multimedia deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Multimedia not found", HttpStatus.NOT_FOUND);
    }
}
