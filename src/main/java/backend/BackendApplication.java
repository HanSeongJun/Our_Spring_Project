package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
														+ "classpath:application.yaml,"
														+ "classpath:api.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(BackendApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
//		SpringApplication.run(BackendApplication.class, args);
	}
}
