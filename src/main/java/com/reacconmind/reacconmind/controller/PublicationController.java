package com.reacconmind.reacconmind.controller;
import com.reacconmind.reacconmind.dto.PublicationDTO;
import com.reacconmind.reacconmind.model.Publication;
import com.reacconmind.reacconmind.repository.PublicationRepository;
import com.reacconmind.reacconmind.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;


@RestController
@RequestMapping("/ReacconMind/publications")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
public class PublicationController {

    @Autowired
    private PublicationService service;

    @Operation(summary = "Get all publications")
    @ApiResponse(responseCode = "200", description = "Found Publications", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Publication.class))) })
    @GetMapping
    public List<Publication> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get a publication by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Publication.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid publication ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Publication not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Publication publication = service.getByIdPublication(id);
        return new ResponseEntity<Publication>(publication, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Publication publication) {
        service.save(publication);
        return new ResponseEntity<String>("Publication created", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody Publication publication, @PathVariable Integer id) {
        Publication existingPublication = service.getByIdPublication(id);
        publication.setIdPublication(existingPublication.getIdPublication());
        service.save(publication);
        return new ResponseEntity<String>("Publication updated", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<String>("Publication deleted", HttpStatus.OK);
    }

    @Autowired
    private PublicationRepository publicationRepository;
    @GetMapping("/publications/juan")
    public List<PublicationDTO> getAllPublications() {
        return publicationRepository.findAllPublications();
    }





}
