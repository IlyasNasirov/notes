package com.example.frontend.service;

import com.example.notes.entity.MyUser;
import com.example.notes.entity.Note;

import java.util.List;

public interface ClientService {

    String createUser(MyUser user);

    List getAllNotes(String username);

    Note getNoteById(int id);

    String addNotes(String username, Note note);

    void deleteNote(String username, int id);

    void updateNote(Note note, int id, String username);



}
