package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    //add credentials
    @PostMapping("/credential")
    public String createOrUpdateCredential(@ModelAttribute("newCred") CredentialForm newCred, Model model) {

        Credentials credentials = new Credentials();

        String url = newCred.getUrl();
        credentials.setUrl(url);
        String username = newCred.getUsername();
        credentials.setUsername(username);
        String password = newCred.getPassword();
        credentials.setPassword(password);

        if (newCred.getCredentialId() != null) {
            credentialService.updateCredential(credentials);
            model.addAttribute("updateCredentialSuccess", true);
        } else {
            credentialService.addCred(credentials);
            model.addAttribute("addCredentialSuccess", true);

        }
        return "result";
    }


    @GetMapping("/credential-delete/{credential_id}")
    public String deleteCredential(@PathVariable("credential_id") Integer credentialId, Model model) {
        model.addAttribute("deleteCredentialSuccess", credentialService.deleteCredential(credentialId));
        return "result";
    }

}
