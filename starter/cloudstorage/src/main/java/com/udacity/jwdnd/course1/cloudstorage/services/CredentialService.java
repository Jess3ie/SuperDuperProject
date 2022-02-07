package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private EncryptionService encryptionService;
    private CredMapper credMapper;

    public CredentialService(EncryptionService encryptionService, CredMapper credMapper) {
        this.encryptionService = encryptionService;
        this.credMapper = credMapper;
    }

    //add cred
    public int addCred(Credentials credentials) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);
        credentials.setKey(encodedKey);
        credentials.setPassword(encryptedPassword);
        return this.credMapper.addCredentials(credentials);
    }

    //update cred
    public int updateCredential(Credentials credentials) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);
        credentials.setKey(encodedKey);
        credentials.setPassword(encryptedPassword);
        return this.credMapper.updateCredentials(credentials);
    }

    //delete cred
    public Integer deleteCredential(Integer credentialId){
        return credMapper.deleteCredential(credentialId);
    }

    //Get all creds
    public List<Credentials> getAllCredentials() {
        return credMapper.getAllCredentials();
    }


}
