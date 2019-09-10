package com.qa.controllers;

import com.qa.models.Note;
import com.qa.repository.NotesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesControllerTest {

    @InjectMocks
    private NotesController notesController;

    @Mock
    private NotesRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllNotes(){
        //create dummy database to access
        List<Note> notesList = new ArrayList<>();
        Note note = new Note();
        note.setDescription("stuff");
        note.setName("stuff");
        notesList.add(note);

        when(repository.findAll()).thenReturn(notesList);

        assertEquals(notesController.listAllNotes().get(0).getName(),"stuff");

    }
    @Test
    public void testGetNote(){
        Note note = new Note();
        note.setName("stuff");

        when(repository.findOne(0L)).thenReturn(note);

        assertEquals(notesController.getNote(0L).getName(),"stuff");

    }
    @Test
    public void testCreateNote(){
        Note note = new Note();
        note.setName("stuff");

        when(repository.saveAndFlush(note)).thenReturn(note);

        assertEquals(notesController.addNote(note).getName(),"stuff");

    }
    @Test
    public void testDeleteNote(){
        Note note = new Note();
        note.setName("nuyce");

        when(repository.findOne(0L)).thenReturn(note);

        assertEquals(notesController.deleteNote(0L).getName(),"nuyce");

    }
}
