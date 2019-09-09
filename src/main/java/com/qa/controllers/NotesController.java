package com.qa.controllers;

import com.qa.models.Note;
import com.qa.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class NotesController {

    @Autowired
    private NotesRepository repository;

    @RequestMapping(value = "notes",method = RequestMethod.GET)
    public List<Note> listAllNotes(){
        return repository.findAll();
    }

    @RequestMapping(value = "notes",method = RequestMethod.POST)
    public Note addNote(@RequestBody Note note){
        return repository.saveAndFlush(note);
    }

}
