package com.ReacconMind.ReacconMind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.model.publicationHashtag;
import com.ReacconMind.ReacconMind.service.publicationHashtagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("publicationHashtag")
@CrossOrigin(origins="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT})
@Tag(name ="Publication hashtag details", description = "Provides methods for managing details of publication and hashtags relationship")
public class publicationHashtagController {
    @Autowired
    private publicationHashtagService service;

    @Operation(summary ="Get all publication hashtag elements")
    @ApiResponse(responseCode = "200", description = "Found publication hashtags elements", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = publicationHashtag.class))) })
    @GetMapping
    public List<publicationHashtag> getAll(){
        return service.getAll();
    }
}
