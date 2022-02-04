package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private NoteService noteService;
    private FileSystemStorageService fileSystemStorageService;
    private EncryptionService encryptionService;
    private CredentialService credentialService;
    private UserService userService;

    @Autowired
    public HomeController(NoteService noteService, FileSystemStorageService fileSystemStorageService, EncryptionService encryptionService, CredentialService credentialService, UserService userService) {
        this.noteService = noteService;
        this.fileSystemStorageService = fileSystemStorageService;
        this.encryptionService = encryptionService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {

        List<Notes> notes = noteService.getAllNotes();
        model.addAttribute("userNotes", notes);

        List<Credentials> credentials = credentialService.getAllCredentials();
        model.addAttribute("userCredentials", credentials);

        List<Files> files = fileSystemStorageService.getAllFiles();
        model.addAttribute("userFiles", files);

        return "home";
    }
}


