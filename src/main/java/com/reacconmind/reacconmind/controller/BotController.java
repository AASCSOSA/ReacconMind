package com.reacconmind.reacconmind.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.reacconmind.reacconmind.dto.BotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PutMapping;

import com.reacconmind.reacconmind.model.Bot;
import com.reacconmind.reacconmind.service.BotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Bot")
@RequestMapping("ReacconMind/bots")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class BotController {
    @Autowired
    private BotService service;

    //@Operation(summary = "Create a Bot", description = "Create a new bot.")
    //@ApiResponse(responseCode = "200", description = "Bot created correctly.", content = {
      //      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    //@PostMapping
    //public ResponseEntity<String> addBot(@RequestBody BotDTO botDTO) {
     //   Bot bot = service.convertToEntity(botDTO);
      //  service.save(bot);
        //return ResponseEntity.ok("Bot added successfully");
    //}

    @PostMapping
    public  ResponseEntity<String> addBot (@RequestBody Bot bot){
        service.save(bot);
        return ResponseEntity.ok("Bot added successfully");
    }


    @Operation(summary = "Get all Bots", description = "Gets a list of all registered bots.")
    @ApiResponse(responseCode = "200", description = "List of bots obtained correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @GetMapping
    public List<BotDTO> getAllBots() {
        return service.getAll().stream().map(service::convertToDTO).collect(Collectors.toList());
    }

    @Operation(summary = "Get a Bot", description = "Get a bot using your identifier.")
    @ApiResponse(responseCode = "200", description = "Bot obtained correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @GetMapping("/{idBot}")
    public ResponseEntity<BotDTO> getBotById(@PathVariable Integer idBot) {
        Bot bot = service.getBotById(idBot);
        return ResponseEntity.ok(service.convertToDTO(bot));
    }

/*     @Operation(summary = "Update a Bot", description = "Update an existing bot.")
    @ApiResponse(responseCode = "200", description = "Bot updated correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @PutMapping("/{idBot}")
    public ResponseEntity<BotDTO> updateBot(@PathVariable int idBot, @RequestBody BotDTO botDTO) {
        try {
            Bot updatedBot = service.convertToEntity(botDTO);
            Bot bot = service.updateBot(idBot, updatedBot);
            return ResponseEntity.ok(service.convertToDTO(bot));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    } */

    @Operation(summary = "Delete a Bot", description = "Delete an existing bot.")
    @ApiResponse(responseCode = "200", description = "Bot deleted correctly", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BotDTO.class))) })
    @DeleteMapping("/{idBot}")
    public ResponseEntity<Void> deleteBot(@PathVariable int idBot) {
        try {
            service.deleteBot(idBot);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}