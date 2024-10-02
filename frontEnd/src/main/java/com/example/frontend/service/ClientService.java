package com.example.frontend.service;

import com.example.notes.dto.LoginDto;
import com.example.notes.dto.MyUserDto;
import com.example.notes.dto.NoteDto;
import com.example.notes.entity.MyUser;

import java.util.List;

public interface ClientService {

    List<NoteDto> getAllNotes(String username);

    NoteDto getNoteById(String username, int noteId);

    void addNote(String username, NoteDto noteDto);

    void deleteNoteById(String username, int noteId);

    NoteDto updateNote(String username, int noteId, NoteDto noteDto);

    MyUserDto login(LoginDto loginDto);

    MyUserDto registerUser(MyUserDto userDto);

    void logout();
}
