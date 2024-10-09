package com.ReacconMind.ReacconMind.service;

import java.util.List;

import com.ReacconMind.ReacconMind.model.Image;
import com.ReacconMind.ReacconMind.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository repo;

    // Obtener todas las imágenes
    public List<Image> getAll() {
        return repo.findAll();
    }

    // Guardar una nueva imagen
    public void save(Image image) {
        repo.save(image);
    }

    // Obtener una imagen por su ID
    public Image getByIdImage(Integer idImage) {
        return repo.findById(idImage).orElse(null); // Devuelve null si no se encuentra
    }

    // Eliminar una imagen por su ID
    public void delete(Integer idImage) {
        repo.deleteById(idImage);
    }
}
