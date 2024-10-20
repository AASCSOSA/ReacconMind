package com.reacconmind.reacconmind.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FirebaseUser {
    private static final String BUCKET_NAME = "reacconmind-e99ee.appspot.com"; // Cambia al nombre de tu bucket

    public String upload(MultipartFile multipartFile) {
        try {
            // Verificar que el archivo no esté vacío
            if (multipartFile.isEmpty()) {
                return "The image file is empty.";
            }

            // Generar nombres de archivo únicos
            String originalFileName = generateUniqueFileName(multipartFile.getOriginalFilename());
            String thumbnailFileName = "thumb_" + originalFileName;

            // Convertir el archivo a File
            File originalFile = convertToFile(multipartFile, originalFileName);
            // Crear miniatura
            File thumbnailFile = createThumbnail(originalFile, thumbnailFileName);

            // Subir el archivo original
            String originalUrl = uploadFile(originalFile, originalFileName);
            // Subir la miniatura
            String thumbnailUrl = uploadFile(thumbnailFile, thumbnailFileName);

            // Eliminar archivos temporales después de subir
            originalFile.delete();
            thumbnailFile.delete();

            return String.format("Original URL: %s, Thumbnail URL: %s", originalUrl, thumbnailUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return "The image could not be uploaded due to an I/O error.";
        } catch (Exception e) {
            e.printStackTrace();
            return "The image could not be uploaded.";
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String extension = getExtension(originalFileName);
        return UUID.randomUUID().toString() + extension;
    }

    private String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex); // Manejar archivos sin extensión
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private File createThumbnail(File originalFile, String thumbnailFileName) throws IOException {
        File thumbnailFile = new File(thumbnailFileName);
        Thumbnails.of(originalFile)
                .size(150, 150)
                .toFile(thumbnailFile);
        return thumbnailFile;
    }

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build(); // Ajustar el tipo de
                                                                                              // contenido según el
                                                                                              // archivo
        InputStream inputStream = ImageService.class.getClassLoader()
                .getResourceAsStream("firebase-privateAlain-key.json");

        // Cargar las credenciales de Google
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // Subir el archivo a Google Cloud Storage
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        // Construir la URL de descarga
        String downloadURL = "https://firebasestorage.googleapis.com/v0/b/" + BUCKET_NAME + "/o/%s?alt=media";
        return String.format(downloadURL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
}
