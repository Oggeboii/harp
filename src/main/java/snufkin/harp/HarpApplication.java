package snufkin.harp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HarpApplication {

	public static void main(String[] args) {
		// Load .env file before Spring starts
		Dotenv dotenv = Dotenv.configure()
			.ignoreIfMissing()
			.load();
		
		// Set environment variables as system properties for Spring to use
		dotenv.entries().forEach(entry -> 
			System.setProperty(entry.getKey(), entry.getValue())
		);
		
		SpringApplication.run(HarpApplication.class, args);
	}

}
