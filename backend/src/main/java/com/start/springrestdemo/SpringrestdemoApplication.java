package com.start.springrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name="Spring REST Demo API", scheme="bearer", type=SecuritySchemeType.HTTP, in=SecuritySchemeIn.HEADER )
public class SpringrestdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestdemoApplication.class, args);
	}

}
