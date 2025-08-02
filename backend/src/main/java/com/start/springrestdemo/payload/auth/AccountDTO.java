package com.start.springrestdemo.payload.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AccountDTO{
   
    @Email
    @Schema(description="Email" , example="admin@studyeasy.com",requiredMode=RequiredMode.REQUIRED)
    private String email;

    @Size(min=6 , max=20)
    @Schema(description="password" , example="password", maxLength=20 , minLength = 6,requiredMode=RequiredMode.REQUIRED)
    private String password;

}