package com.ReacconMind.ReacconMind.service;

import java.util.List;

import com.ReacconMind.ReacconMind.model.Publication;
import com.ReacconMind.ReacconMind.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class PublicationService {

    @Autowired
    private PublicationRepository repo;

    // Obtener todas las publicaciones
    public List<Publication> getAll() {
        return repo.findAll();
    }

    // Guardar una nueva publicación
    public void save(Publication publication) {
        repo.save(publication);
    }

    // Obtener una publicación por su ID
    public Publication getByIdPublication(Integer idPublication) {
        return repo.findById(idPublication).orElse(null); // Devuelve null si no se encuentra
    }

    // Eliminar una publicación por su ID
    public void delete(Integer idPublication) {
        repo.deleteById(idPublication);
    }
}
