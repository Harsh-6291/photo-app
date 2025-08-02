 package com.start.springrestdemo.config;   
   
   
   
   import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
   import io.swagger.v3.oas.annotations.info.Contact;
   import io.swagger.v3.oas.annotations.info.Info;
   import io.swagger.v3.oas.annotations.info.License;
  
  
  @Configuration
  @OpenAPIDefinition(
    info=@Info(
        title = "Spring REST Demo API",
        version = "1.0.0",
        contact=@Contact(
            name = "Spring REST Demo API",
            email = "harshpachori6921@gmail.com",
            url = "http://studyeasy.org"),
        license = @License(
                  name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                  ),
        termsOfService = "https://studyeasy.org/",
        description = "Spring Boot RestFul API Demo by harsh" )
 )
  public class SwaggerConfig{

  }