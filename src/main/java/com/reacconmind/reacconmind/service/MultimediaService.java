package com.reacconmind.reacconmind.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class MultimediaService {

    // Método principal para subir archivos multimedia
    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            String extension = this.getExtension(fileName);

            // Asignamos una carpeta basada en el tipo de archivo
            String folder = determineFolder(extension);

            if (folder == null) {
                return "Unsupported file type";
            }

            // Generamos un nombre de archivo único
            fileName = folder + "/" + UUID.randomUUID().toString().concat(extension);

            // Convertimos el archivo a un archivo temporal
            File file = this.convertToFile(multipartFile, fileName);

            // Subimos el archivo a Firebase Storage
            String URL = this.uploadFile(file, fileName);

            // Eliminamos el archivo temporal
            file.delete();

            return URL;
        } catch (Exception e) {
            e.printStackTrace();
            return "The file could not be uploaded";
        }
    }

    // Determina la carpeta basada en la extensión del archivo
    private String determineFolder(String extension) {
        if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".png")) {
            return "imageMultimedia";  // Carpeta para imágenes
        } else if (extension.equalsIgnoreCase(".mp3")) {
            return "audioMultimedia";  // Carpeta para audios
        } else if (extension.equalsIgnoreCase(".mp4") || extension.equalsIgnoreCase(".avi") || extension.equalsIgnoreCase(".mov")) {
            return "videoMultimedia";  // Carpeta para videos
        }
        return null;  // Tipo de archivo no soportado
    }

    // Obtiene la extensión del archivo
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    // Convierte MultipartFile a un archivo temporal
    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    // Método para subir el archivo a Firebase Storage
    private String uploadFile(File file, String fileName) throws IOException {
        // Creamos el BlobId con el bucket y el nombre del archivo
        BlobId blobId = BlobId.of("juan-a650b.appspot.com", fileName);  // Cambia tu bucket aquí
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        // Obtenemos las credenciales de Firebase
        InputStream inputStream = MultimediaService.class.getClassLoader().getResourceAsStream("firebase-private-key.json");
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // Subimos el archivo a Firebase Storage
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        // Creamos la URL de descarga del archivo
        String downloadURL = "https://firebasestorage.googleapis.com/v0/b/juan-a650b.appspot.com/o/%s?alt=media";
        return String.format(downloadURL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
}
