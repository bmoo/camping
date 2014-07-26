package org.bradmoore.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Created on 4/21/14.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
public class Application {

	// a command line execution should not be necessary since runtime will be controlled by spring integration
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
