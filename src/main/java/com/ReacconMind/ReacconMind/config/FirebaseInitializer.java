package com.ReacconMind.ReacconMind.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

    public FirebaseInitializer() {
        try {
            Resource resource = new ClassPathResource("reaccoon-mind-firebase-adminsdk-225ue-369dbd59f0.json");
            File file = resource.getFile();
            if (!file.exists()) {
                throw new IOException("File not found: " + resource.getFilename());
            }

            GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .setStorageBucket("reacconmind.appspot.com")  // Sin el prefijo 'gs://'
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error initializing Firebase: " + e.getMessage());
        }
    }
}
