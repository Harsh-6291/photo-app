package com.start.springrestdemo.payload.auth;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountViewDTO {
    
    
    private long id;

    private String email;

    private String  authorities;                
;
    
}
