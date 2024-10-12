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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ReacconMind.ReacconMind.model.Tendency;
import com.ReacconMind.ReacconMind.service.TendencyService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/ReacconMind/users")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Configuration
@OpenAPIDefinition(info = @Info(title = "ReacconMind API", description = "Api para la gestion de tendencias en la aplicacion ReacconMind", version = "1.0"))
public class TendencyController {
    @Autowired
    private TendencyService tendencyService;

    // para obtener a todas las tendencias
    @Operation(summary = "Get all Tendencies", description = "Obtiene una lista de todas las tendencias registradas.")
    @ApiResponse(responseCode = "200", description = "Lista de tendencias obtenida correctamente", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Tendency.class))) })
    @GetMapping
    public List<Tendency> getAll() {
        return tendencyService.getAll();
    }

    // para obtener una tendencia por id
    @Operation(summary = "Get a tendency by ID", description = "Obtiene una tendencia en especifico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tendencia encontrada", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Tendency.class)) }),
            @ApiResponse(responseCode = "400", description = "ID de tendencia invalido", content = @Content),
            @ApiResponse(responseCode = "401", description = "Fallo de autenticación", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Tendencia no encontrada", content = @Content) })
    @GetMapping("/{idTendency}")
    public ResponseEntity<?> getByIdUser(@PathVariable Integer idTendency) {
        Tendency tendency = tendencyService.getByIdTendency(idTendency);
        return new ResponseEntity<Tendency>(tendency, HttpStatus.OK);
    }

    // Para agregar una nueva tendencia
    @Operation(summary = "Add a new Tendency", description = "Agrega una nueva tendencia al sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tendencia agregada exitosamente", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Datos de tendencia inválidos", content = @Content),
    })
    @PostMapping
    public ResponseEntity<String> addTendency(@RequestBody Tendency tendency) {
        tendencyService.save(tendency);
        return ResponseEntity.ok("User added successfully");
    }

    // Para actualizar la tendencia (tambien se va a eliminar)
    @Operation(summary = "Update an existing Tendency", description = "Actualiza los datos de una tendencia existente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro actualizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Tendencia no encontrada", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos de tendencia inválidos", content = @Content) })
    @PutMapping("/update/{idTendency}")
    public ResponseEntity<?> update(@RequestBody Tendency tendency, @PathVariable Integer idTendency) {
        Tendency auxTendency = tendencyService.getByIdTendency(idTendency);
        tendency.setIdTendency(auxTendency.getIdTendency());
        tendencyService.save(tendency);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }
}
