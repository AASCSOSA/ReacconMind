package com.reacconmind.reacconmind.controller;

import com.reacconmind.reacconmind.model.Multimedia;
import com.reacconmind.reacconmind.service.MultimediaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    // Método para subir archivo
    @Operation(summary = "Sube un archivo multimedia",
            description = "Este endpoint permite subir un archivo multimedia y devuelve la URL del archivo.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "object", implementation = MultipartFile.class))))
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @Parameter(description = "Archivo multimedia a subir", required = true)
            @RequestParam("file") MultipartFile file) {
        String url = multimediaService.upload(file);
        return ResponseEntity.ok(url);
    }



    // Método para obtener todos los multimedia (opcional)

}
