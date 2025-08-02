package com.start.springrestdemo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.start.springrestdemo.models.Account;
import com.start.springrestdemo.payload.auth.AccountDTO;
import com.start.springrestdemo.payload.auth.AccountViewDTO;
import com.start.springrestdemo.payload.auth.AuthoritiesDTO;
import com.start.springrestdemo.payload.auth.PasswordDTO;
import com.start.springrestdemo.payload.auth.ProfileDTO;
import com.start.springrestdemo.payload.auth.TokenDTO;
import com.start.springrestdemo.payload.auth.UserLoginDTO;
import com.start.springrestdemo.service.AccountService;
import com.start.springrestdemo.service.TokenService;
import com.start.springrestdemo.utils.constants.AccountError;
import com.start.springrestdemo.utils.constants.AccountSucces;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;





@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@Tag(name = "Authentication", description = "Authentication endpoints")
@Slf4j
public class AuthController {
    @Autowired
    private final TokenService tokenService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

  

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenDTO> token(@Valid@RequestBody UserLoginDTO userLogin) throws AuthenticationException{
        try {   
                Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
                return ResponseEntity.ok(new TokenDTO(tokenService.generateToken(authentication)));
        } catch (Exception e) {
           log.debug(AccountError.TOKEN_GENERATION_ERROR.toString() + " " + e.getMessage() );
           return new ResponseEntity<>(new TokenDTO(null) , HttpStatus.BAD_REQUEST);
        }
        

    }
   
    
    @PostMapping(value="/users/add",  produces="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode="400", description="bad request")
    @ApiResponse(responseCode="200",description="Account added")
    @Operation(summary="add new user")
    public ResponseEntity<String> addUser(@Valid@RequestBody AccountDTO accountDTO) {
    try {
        Account account= new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        accountService.save(account); 
         return ResponseEntity.ok(AccountSucces.ADD_ACCOUNT_SUCCESS.toString());
    } catch (Exception e) {
    log.debug(AccountError.ADD_ACCOUNT_ERROR.toString() + ": " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
    }    
 }
 
    @GetMapping(value="/users", produces="application/json")
    @ApiResponse(responseCode="200",description="list of user")
    @ApiResponse(responseCode="401",description="token missing")
    @ApiResponse(responseCode="403",description="token error")
    @Operation(summary="list user api")
    @SecurityRequirement(name = "Spring REST Demo API")
    public List<AccountViewDTO> users() {
        List<AccountViewDTO> accounts= new ArrayList<>();

        for(Account account:accountService.findAll()){
            accounts.add(new AccountViewDTO(account.getId(), account.getEmail(),account.getAuthorities()));
        }
        return accounts;
 }

    @PutMapping(value="/users/{user_id}/update-authorities", produces="application/json")
    @ApiResponse(responseCode="200",description="authority updated")
    @ApiResponse(responseCode="401",description="token missing")
    @ApiResponse(responseCode="400",description="invalid user id")
    @ApiResponse(responseCode="403",description="token error")
    @Operation(summary="update authority ")
    @SecurityRequirement(name = "Spring REST Demo API")
    public ResponseEntity<AccountViewDTO> update_auth(@Valid @RequestBody AuthoritiesDTO authoritiesDTO,@PathVariable Long user_id) {
      Optional<Account> optionalAccount=accountService.findById(user_id);
      if(optionalAccount.isPresent()){
        Account account=optionalAccount.get();
        account.setAuthorities(authoritiesDTO.getAuthorities());
        accountService.save(account);
        AccountViewDTO accountViewDTO= new AccountViewDTO(account.getId(), account.getEmail(),account.getAuthorities());
        return ResponseEntity.ok(accountViewDTO);      
      }
    return new ResponseEntity<>(new AccountViewDTO() , HttpStatus.BAD_REQUEST);
 }


    @GetMapping(value="/profile", produces="application/json")
    @ApiResponse(responseCode="200",description="your profile")
    @ApiResponse(responseCode="401",description="token missing")
    @ApiResponse(responseCode="403",description="token error")
    @Operation(summary="profile view")
    @SecurityRequirement(name = "Spring REST Demo API")
    public ProfileDTO profile(Authentication authentication) {
        String email=authentication.getName();
      Optional<Account> optionalAccount=accountService.findByEmail(email);
        Account account=optionalAccount.get();
        ProfileDTO profileDTO=new ProfileDTO(account.getId(),account.getEmail(),account.getAuthorities());
        return profileDTO;
  
 }

    

    @PutMapping(value="/profile/update-password", produces="application/json")
    @ApiResponse(responseCode="200",description="password updated")
    @ApiResponse(responseCode="401",description="token missing")
    @ApiResponse(responseCode="403",description="token error")
    @Operation(summary="update password ")
    @SecurityRequirement(name = "Spring REST Demo API")
    public AccountViewDTO update_password(@Valid @RequestBody PasswordDTO passwordDTO,Authentication authentication) {
        String email=authentication.getName();
      Optional<Account> optionalAccount=accountService.findByEmail(email);
        Account account=optionalAccount.get();
        account.setPassword(passwordDTO.getPassword());
        accountService.save(account);
        AccountViewDTO accountViewDTO= new AccountViewDTO(account.getId(), account.getEmail(),account.getAuthorities());
        return accountViewDTO;      
      
 }

    @DeleteMapping(value="/profile/delete")
    @ApiResponse(responseCode="200",description="password updated")
    @ApiResponse(responseCode="401",description="token missing")
    @ApiResponse(responseCode="403",description="token error")
    @Operation(summary="delete profile ")
    @SecurityRequirement(name = "Spring REST Demo API")
    public ResponseEntity<String> delete_profile(Authentication authentication) {
      String email=authentication.getName();
      Optional<Account> optionalAccount=accountService.findByEmail(email);
       if(optionalAccount.isPresent()){
        accountService.deleteById(optionalAccount.get().getId());
         return ResponseEntity.ok("user deleted");
       }
        return new ResponseEntity<>("bad request",HttpStatus.BAD_REQUEST);
      
 }

}
