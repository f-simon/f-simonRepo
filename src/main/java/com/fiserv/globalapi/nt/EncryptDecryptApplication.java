package com.fiserv.globalapi.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootApplication 
public class EncryptDecryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptDecryptApplication.class, args);
	}
	
	@Bean
	public ObjectMapper objectMapper() {
	   ObjectMapper mapper = new ObjectMapper();
	   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	   return mapper;
	}

}
