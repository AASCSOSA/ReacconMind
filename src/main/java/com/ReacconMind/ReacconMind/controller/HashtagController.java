package com.ReacconMind.ReacconMind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.model.Hashtag;
import com.ReacconMind.ReacconMind.service.HashtagService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("ReacconMind/hashtags")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Configuration
@OpenAPIDefinition(info = @Info(title = "ReacconMind API", description = "API para la gestion de Hashtags en ReacconMind", version = "1.0"))
public class HashtagController {

    @Autowired
    private HashtagService hashtagService;

    //Se obtienen todos los Hashtags
    @Operation(summary = "Get all Hashtags", description= "Obtiene una lista de todos los hashtags existentes.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Hashtag.class ))) })
    @GetMapping
    public List<Hashtag> getAll(){
        return hashtagService.getAll();
    }

    //Se obtiene un Hashtag por su ID
    @Operation(summary = "Get a Hashtag by ID", description = "Obtiene un Hashtag especifico por su ID.")
    @ApiResponses(value ={
        @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Hashtag.class)) }),
            @ApiResponse(responseCode = "400", description = "ID de Hashtag inválido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Fallo de autenticación", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Hashtag no encontrado", content = @Content) })
    @GetMapping("/{idHashtag}")
    public ResponseEntity<?> getByIdHashtag(@PathVariable Integer idHashtag){
        Hashtag hashtag = hashtagService.getByIdHashtag(idHashtag);
        return new ResponseEntity<Hashtag>(hashtag, HttpStatus.OK);
    }

    //Se agrega un nuevo hashtag
    @Operation(summary = "Add a new Hashtag" , description = "Agrega un nuevo hashtag al sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200" , description = "Hashtag ha sido agregado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
        @ApiResponse(responseCode = "400", description = "Datos de usuario invalidos", content =@Content),
    }) 
    @PostMapping
    public ResponseEntity<String> addHashtag(@RequestBody Hashtag hashtag){
        hashtagService.save(hashtag);
        return ResponseEntity.ok("Hashtag added successfully");
    }

    //Para actualizar el hashtag (despues no será necesario)
    @Operation(summary = "Update an existing hashtag", description = "Actualiza un hashtag existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hashtag actualizaco", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
        @ApiResponse(responseCode = "404", description = "Hashtag no encontrado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Datos de Hashtag inválidos", content=@Content) })
    @PutMapping("/update/{idHashtag}")
    public ResponseEntity<?> update (@RequestBody Hashtag hashtag, @PathVariable Integer idHashtag){
        Hashtag auxHashtag = hashtagService.getByIdHashtag(idHashtag);
        hashtag.setIdHashtag(auxHashtag.getIdHashtag());
        hashtagService.save(hashtag);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }
}
