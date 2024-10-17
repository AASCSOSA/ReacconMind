package com.ReacconMind.ReacconMind.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseService {

    // Nombre del bucket de Firebase Storage
    private static final String BUCKET_NAME = "reacconmind.appspot.com"; // Reemplázalo con el nombre de tu bucket

    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public String uploadFile(MultipartFile file) throws IOException {
        // Generar un nombre de archivo único usando UUID
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Obtener una referencia al bucket de Firebase
        Bucket bucket = storage.get(BUCKET_NAME);


        // Subir el archivo a Firebase Storage
        Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

        // Hacer que el archivo sea accesible públicamente y obtener la URL pública
        return blob.getMediaLink();
    }
}
