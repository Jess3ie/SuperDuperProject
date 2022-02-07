package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Notes> getAllNotes() {
        return noteMapper.getAllNotes();
    }

    //Add Note
    public void addNote(Notes notes){

        noteMapper.addUserNote(notes);
//        // Display all notes in database to check if note is added successfully
//        ArrayList<Note> result = noteMapper.getAllNotes();

    }
    //Update Note

    public void updateNote(Integer noteId, String noteTitle, String noteDescription) {
       noteMapper.updateUserNote(noteId, noteTitle, noteDescription);
    }

    //Delete Note
    public Integer deleteNote(Integer noteId) {
        return noteMapper.deleteNote(noteId);
    }



}
