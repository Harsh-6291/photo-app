package com.start.springrestdemo.security;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Component;


@Component
final class KeyGeneratorUtils {

    private  KeyGeneratorUtils() {}
    
 static KeyPair generatorRsaKey(){
    KeyPair keyPair;
    try{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
        }catch (Exception ex) {
        throw new IllegalStateException("Error generating RSA key pair", ex);
       }
    return keyPair;
 }
  
}
