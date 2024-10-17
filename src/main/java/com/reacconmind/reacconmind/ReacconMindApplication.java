package com.reacconmind.reacconmind;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableWebSecurity
public class ReacconMindApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReacconMindApplication.class, args);
  }
}
