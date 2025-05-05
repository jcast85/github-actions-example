package com.jc.crud.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
public class EnvLoggerConfig {

  @Bean
  public CommandLineRunner printEnvVariables() {
    return args -> {
      log.info("----- Printing Environment Variables -----");
      for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
        log.info("{} = {}", entry.getKey(), entry.getValue());
      }
      log.info("----- End of Environment Variables -----");
    };
  }
}
