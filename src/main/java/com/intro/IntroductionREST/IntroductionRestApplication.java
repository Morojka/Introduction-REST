package com.intro.IntroductionREST;

import com.intro.IntroductionREST.wsdl.ProcessPersonResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IntroductionRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(IntroductionRestApplication.class, args);
	}
}