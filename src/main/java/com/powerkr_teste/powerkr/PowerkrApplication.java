package com.powerkr_teste.powerkr;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Powerkr Teste técnico", version = "1", description = "API desenvolvida para teste técnico da Powerkr"))
public class PowerkrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerkrApplication.class, args);
	}

}
