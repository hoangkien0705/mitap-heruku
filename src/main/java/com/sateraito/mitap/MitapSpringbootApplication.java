package com.sateraito.mitap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sateraito.mitap.service.MitapService;

@SpringBootApplication
public class MitapSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitapSpringbootApplication.class, args);
		MitapService.inserErrorCode();
		
	}

}
