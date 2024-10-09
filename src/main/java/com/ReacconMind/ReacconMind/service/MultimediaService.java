package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.Multimedia;
import com.ReacconMind.ReacconMind.repository.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository repository;

    // Método para obtener todas las entradas de multimedia
    public List<Multimedia> getAll() {
        return repository.findAll();
    }

    // Método para obtener una entrada de multimedia por su ID
    public Multimedia getById(int id) {
        Optional<Multimedia> optionalMultimedia = repository.findById(id);
        return optionalMultimedia.orElse(null);
    }

    // Método para guardar una nueva entrada de multimedia o actualizar una existente
    public void save(Multimedia multimedia) {
        repository.save(multimedia);
    }

    // Método para eliminar una entrada de multimedia por su ID
    public void delete(int id) {
        repository.deleteById(id);
    }
}
