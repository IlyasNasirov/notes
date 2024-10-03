package com.example.frontend.controller;

import com.example.frontend.service.ClientService;
import com.example.notes.dto.NoteDto;
import com.example.notes.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String MainPage() {
        return "home";
    }

    @GetMapping("/{username}")
    public String UserMenu(@PathVariable String username,
                           Model model) {

        model.addAttribute("username", username);
        return "user_info";
    }


    @GetMapping("/{username}/all_note")
    public String getAllNotes(@PathVariable String username,
                              Model model) {

        List<NoteDto> notes = clientService.getAllNotes(username);
        model.addAttribute("notes", notes);
        return "all_notes";
    }

    @GetMapping("{username}/delete_note")
    public String deleteNote(@PathVariable String username,
                             @RequestParam("id") int noteId) {

        clientService.deleteNoteById(username, noteId);
        return "redirect:/notes/{username}/all_note";
    }

    @GetMapping("{username}/update_note")
    public String updateNote(@PathVariable String username,
                             @RequestParam("noteId") int noteId,
                             Model model) {

        NoteDto noteDto = clientService.getNoteById(username, noteId);
        model.addAttribute("note", noteDto);
        return "update_note";
    }

    @PostMapping("{username}/update_note")
    public String updatedNote(@PathVariable String username,
                              @RequestParam int noteId,
                              @ModelAttribute NoteDto noteDto) {

        clientService.updateNote(username, noteId, noteDto);
        return "redirect:/notes/{username}/all_note";
    }

    @GetMapping("/{username}/note")
    public String createNote(@PathVariable String username,
                             Model model) {

        model.addAttribute("note", new Note());
        return "add_note";
    }

    @PostMapping("/{username}/note")
    public String saveNote(@PathVariable String username,
                           @ModelAttribute NoteDto noteDto) {

        clientService.addNote(username, noteDto);
        return "redirect:/notes/{username}/all_note";
    }

}