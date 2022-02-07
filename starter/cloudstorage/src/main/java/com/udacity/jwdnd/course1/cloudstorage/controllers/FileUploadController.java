package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileSystemStorageService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {

    private FileSystemStorageService fileSystemStorageService;
    private UserService userService;

    public FileUploadController(FileSystemStorageService fileSystemStorageService, UserService userService) {
        this.fileSystemStorageService = fileSystemStorageService;
        this.userService = userService;
    }

    @PostMapping("/file-upload")
//    public String insertFile(@RequestParam("fileUpload") MultipartFile fileUpload, @ModelAttribute("newFile") FileForm newFile, Model model) throws IOException
    public String insertFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication) throws IOException {
        if (!fileUpload.isEmpty()) {
            String username = authentication.getName();
            User user = userService.getUser(username);

            int rowAdded = 0;
            try {
                rowAdded = fileSystemStorageService.insertFile(new Files(0, fileUpload.getOriginalFilename(), fileUpload.getContentType(), fileUpload.getSize(), user.getUserId(), fileUpload.getBytes()));
            } catch (IOException e) {
                throw e;
            }
            if (rowAdded > 0) return "redirect:/result?success";
            else if (rowAdded == 0) return "redirect:/result?duplicateFile";
            else return "redirect:/result?error";
        } else return "redirect:/result?error";


    }

    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam("fileId") Integer fileId) {
        if (fileId != null) {
            this.fileSystemStorageService.deleteFile(fileId);
            return "redirect:/result?success";
        } else return "redirect:/result?error";
    }

    //    @GetMapping("/files/download")
    @GetMapping("/files/view")
    public ResponseEntity downloadFile(@RequestParam("fileId") Integer fileId, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUser(username);
        Files files = fileSystemStorageService.getFiles(user.getUserId(), fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + files.getFilename() + "\"")
                .body(new ByteArrayResource(files.getFileData()));

    }
}




