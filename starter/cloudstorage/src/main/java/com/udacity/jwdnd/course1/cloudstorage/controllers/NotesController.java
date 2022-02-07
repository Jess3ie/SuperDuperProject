package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotesController {

    private UserService userService;
    private NoteService noteService;

    public NotesController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    //add note
    @PostMapping("/note")
    public String addNote(@ModelAttribute("newNote") NoteForm newNote, Model model) {

        Notes notes = new Notes();

        String title = newNote.getNoteTitle();
        notes.setNoteTitle(title);

        String description = newNote.getNoteDescription();
        notes.setNoteDescription(description);

        int rowAdded = 0;

        if (newNote.getNoteId() != null) {
            noteService.updateNote(newNote.getNoteId(), title, description);
            model.addAttribute("updateNoteSuccess", true);

        } else {
            noteService.addNote(notes);
            model.addAttribute("addNoteSuccess", true);
        }
        return "result";
    }

    @GetMapping("/note-delete/{note_id}")
    public String deleteNote(@PathVariable("note_id") Integer noteId, Model model) {
    model.addAttribute("deleteNoteSuccess", noteService.deleteNote(noteId));
    return "result";
    }
}
