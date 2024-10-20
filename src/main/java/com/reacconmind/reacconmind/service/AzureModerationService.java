package com.reacconmind.reacconmind.service;

import com.reacconmind.reacconmind.config.AzureConfig;
import com.reacconmind.reacconmind.model.ModerationResult;
import com.reacconmind.reacconmind.enums.ModerationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class AzureModerationService {
    @Autowired
    private AzureConfig azureConfig;
    
    @Autowired
    private RestTemplate restTemplate;

    public ModerationTypeEnum moderateText(String content) {
        ModerationResult result = moderateContent(content);
        return result.getDecision();
    }

    public ModerationTypeEnum moderateImage(MultipartFile image) {
        try {
            String url = azureConfig.getEndpoint() + "/contentmoderator/moderate/v1.0/ProcessImage/Evaluate";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("Ocp-Apim-Subscription-Key", azureConfig.getKey());
            
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("Image", image.getBytes());
            
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
            );
            
            return processImageModerationResponse(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
            return ModerationTypeEnum.PENDING;
        }
    }

    private ModerationTypeEnum processImageModerationResponse(String response) {
        // Implementar lógica de procesamiento de respuesta de Azure para imágenes
        // Este es un ejemplo simplificado
        if (response.contains("\"IsImageAdultClassified\":false") && 
            response.contains("\"IsImageRacyClassified\":false")) {
            return ModerationTypeEnum.APPROVED;
        }
        return ModerationTypeEnum.REJECTED;
    }

    private ModerationResult moderateContent(String content) {
        String url = azureConfig.getEndpoint() + "/contentmoderator/moderate/v1.0/ProcessText/Screen";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set("Ocp-Apim-Subscription-Key", azureConfig.getKey());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(content, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            String.class
        );

        return processTextModerationResponse(response);
    }

    private ModerationResult processTextModerationResponse(ResponseEntity<String> response) {
        ModerationResult result = new ModerationResult();
        
        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            
            // Implementar lógica detallada de procesamiento de respuesta
            boolean hasInappropriateContent = body.contains("\"Terms\":[") || 
                                            body.contains("\"Classification\":{\"ReviewRecommended\":true}");
            
            result.setDecision(hasInappropriateContent ? ModerationTypeEnum.REJECTED : ModerationTypeEnum.APPROVED);
            result.setDetails(body);
        } else {
            result.setDecision(ModerationTypeEnum.PENDING);
            result.setDetails("Error en la moderación: " + response.getStatusCode());
        }
        
        return result;
    }
}