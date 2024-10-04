package com.ReacconMind.ReacconMind.controller;

import com.reacoonmind.model.TendencyHashtag;
import com.reacoonmind.service.TendencyHashtagService;
import com.reacoonmind.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tendency-hashtags")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TendencyHashtagController {

    @Autowired
    private TendencyHashtagService tendencyHashtagService;

    // Los métodos del controlador permanecen sin cambios
    // ...
}