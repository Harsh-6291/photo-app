package com.start.springrestdemo.payload.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthoritiesDTO {
   
    @NotBlank
    @Schema(description="authority", example="USER", requiredMode=RequiredMode.REQUIRED)
    private String Authorities;
    
}
