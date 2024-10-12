package com.ReacconMind.ReacconMind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.model.tendencyHashtag;
import com.ReacconMind.ReacconMind.service.tendencyHashtagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("tendencyHashtag")
@CrossOrigin(origins ="*", methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE,
    RequestMethod.PUT})
@Tag(name="Tendency and hashtag relationship details", description="Provides methods for managing details of the tendency and hashag relationship")
public class tendencyHashtagController {
    @Autowired
    private tendencyHashtagService service;

    @Operation(summary = "Get all hashtag and tendency")
	@ApiResponse(responseCode = "200", description = "Found tendency hashtag", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = tendencyHashtag.class))) })
	@GetMapping
	public List<tendencyHashtag> getAll() {
		return service.getAll();
	}
}
