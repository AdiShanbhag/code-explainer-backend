package com.ai.codeexplainer;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeexplainerApplication {

	public static void main(String[] args) {
		// Load .env only in dev (optional: add condition or profile check)
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();

		// Set environment variable for Spring
		System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));
		
		SpringApplication.run(CodeexplainerApplication.class, args);
	}

}
