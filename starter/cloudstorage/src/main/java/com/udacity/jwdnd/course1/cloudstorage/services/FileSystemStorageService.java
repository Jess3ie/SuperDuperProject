package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileSystemStorageService {

    private FileMapper fileMapper;

    public FileSystemStorageService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<Files> getAllFiles(){
        return fileMapper.getAllFiles();
    }

    public int insertFile(Files files) {
        if(!findFileByName(files.getFilename())){
            return this.fileMapper.insertFile(files);
        }
        else return 0;
    }

    public boolean findFileByName(String filename){
        if(this.fileMapper.getFileByName(filename) != null) return true; //File found
        else return false; //File not found
    }

    public void deleteFile(Integer fileId) {
        this.fileMapper.deleteFile(fileId);
    }

    public Files getFiles(Integer userid, Integer fileId) {
        return this.fileMapper.getFile(userid, fileId);
    }

}
