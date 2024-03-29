package org.launchcode.projectliftoff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
public class ProjectLiftoffApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProjectLiftoffApplication.class, args);
	}

}
