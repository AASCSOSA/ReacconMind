package com.reacconmind.reacconmind.service;

import com.reacconmind.reacconmind.model.Multimedia;
import com.reacconmind.reacconmind.repository.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;

    // Obtener todos los elementos multimedia
    public List<Multimedia> getAll() {
        return multimediaRepository.findAll();
    }

    // Obtener un elemento multimedia por su ID
    public Optional<Multimedia> getById(Integer id) {
        return multimediaRepository.findById(id); // Devuelve un Optional
    }

    // Crear o actualizar un elemento multimedia
    public Multimedia save(Multimedia multimedia) {
        return multimediaRepository.save(multimedia);
    }

    // Eliminar un elemento multimedia por su ID
    public boolean delete(Integer id) {
        if (multimediaRepository.existsById(id)) {
            multimediaRepository.deleteById(id);
            return true; // Eliminación exitosa
        }
        return false; // El elemento no se encontró
    }
}
