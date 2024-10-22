package com.reacconmind.reacconmind.controller;

import com.reacconmind.reacconmind.model.Reaction;
import com.reacconmind.reacconmind.model.ReactionPK;
import com.reacconmind.reacconmind.service.ReactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reactions")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Reaction Management", description = "Provides methods for managing user reactions to publications and comments")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @Operation(summary = "Get all reactions")
    @ApiResponse(responseCode = "200", description = "Found all reactions", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Reaction.class)))
    })
    @GetMapping
    public List<Reaction> getAllReactions() {
        return reactionService.getAllReactions();
    }

    @Operation(summary = "Create a new reaction")
    @ApiResponse(responseCode = "201", description = "Reaction created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Reaction.class))
    })
    @PostMapping
    public Reaction createReaction(@RequestBody Reaction reaction) {
        return reactionService.saveReaction(reaction);
    }

    @Operation(summary = "Get reactions by user ID")
    @ApiResponse(responseCode = "200", description = "Found reactions for user", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Reaction.class)))
    })
    @GetMapping("/user/{idUser}")
    public List<Reaction> getReactionsByUser(@PathVariable int idUser) {
        return reactionService.getReactionsByUser(idUser);
    }

    @Operation(summary = "Get reactions by publication ID")
    @ApiResponse(responseCode = "200", description = "Found reactions for publication", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Reaction.class)))
    })
    @GetMapping("/publication/{idPublication}")
    public List<Reaction> getReactionsByPublication(@PathVariable int idPublication) {
        return reactionService.getReactionsByPublication(idPublication);
    }

    @Operation(summary = "Delete a reaction")
    @ApiResponse(responseCode = "204", description = "Reaction deleted successfully")
    @DeleteMapping("/{idUser}/{idPublication}/{idComment}")
    public void deleteReaction(@PathVariable int idUser, @PathVariable int idPublication) {
        ReactionPK reactionPK = new ReactionPK(idUser, idPublication);
        reactionService.deleteReaction(reactionPK);
    }
}
