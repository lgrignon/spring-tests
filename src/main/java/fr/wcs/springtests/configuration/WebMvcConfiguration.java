package fr.wcs.springtests.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(WebMvcConfiguration.class, args);
	}

}
