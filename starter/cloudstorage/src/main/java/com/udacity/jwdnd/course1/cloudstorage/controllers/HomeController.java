package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class HomeController {


    @Autowired private NoteService noteService;

@GetMapping("/home")
    public String getHomePage(Model model) {

       // model.addAtrribute("logout");

    List<Notes> notes = noteService.getAllNotes();

    model.addAttribute("existingNotes", notes);
        return "home";
    }

    @PostMapping("/file-upload")
    public InputStream handleFileUpload(@RequestParam("fileUpload")MultipartFile fileUpload, Model model) throws IOException {
        InputStream fis = fileUpload.getInputStream();
        return fis;
    }
}
