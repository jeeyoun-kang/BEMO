package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class HelloSpringApplication {
//
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
