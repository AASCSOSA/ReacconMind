package com.ReacconMind.ReacconMind.service;

import com.ReacconMind.ReacconMind.model.Image;
import com.ReacconMind.ReacconMind.repository.ImageRepository;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository repo;

    // Configuración del bucket de Firebase
    private final String BUCKET_NAME = "reacconmind.appspot.com"; // Cambia esto por el nombre de tu bucket

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

    // Método para subir la imagen a Firebase
    public String uploadImage(byte[] fileBytes, String fileName) throws IOException {
        // Inicializa el cliente de almacenamiento
        Storage storage = StorageOptions.getDefaultInstance().getService();
        // Crea un nuevo blob (objeto) en el bucket
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Bucket bucket = storage.get(BUCKET_NAME);
        Blob blob = bucket.create(uniqueFileName, fileBytes, "image/jpeg");
        String url = "https://storage.googleapis.com/" + BUCKET_NAME + "/" + uniqueFileName;
        // Crea una nueva instancia de Image y guarda en la base de datos
        Image image = new Image();
        image.setUrl(url);
        image.setUploadDate(new Timestamp(System.currentTimeMillis()));
        save(image);
        return url; // Devuelve la URL de la imagen subida
    }
}
