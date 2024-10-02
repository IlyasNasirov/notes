package com.example.frontend.controller;

import com.example.frontend.service.ClientService;
import com.example.notes.entity.MyUser;
import com.example.notes.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class ClientController {

    @Autowired
    ClientService service;

    @GetMapping
    public String MainPage() {
        return "home";
    }

    @PostMapping
    public String saveUser(@ModelAttribute MyUser user) {
        service.createUser(user);
        return "user_info";
    }

    @GetMapping("/new_user")
    public String createUser(Model model) {
        model.addAttribute("newUser", new MyUser());
        return "registration";
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{username}")
    public String UserMenu(@PathVariable String username, Model model) {

        model.addAttribute("username", username);
        return "user_info";
    }


    @GetMapping("/{username}/all_note")
    public String getAllNotes(@PathVariable String username, Model model) {
        List<Note> notes = service.getAllNotes(username);
        model.addAttribute("notes", notes);
        return "all_notes";
    }

    @GetMapping("{username}/delete_note")
    public String deleteNote(@PathVariable String username,
                             @RequestParam("id") int id) {
        service.deleteNote(username, id);
        return "redirect:/notes/{username}/all_note";
    }

    @GetMapping("{username}/update_note")
    public String updateNote(@RequestParam("id") int id,
                             Model model) {
        Note note = service.getNote(id);
        model.addAttribute("note", note);
        return "update_note";
    }

    @PostMapping("{username}/update_note")
    public String updatedNote(@PathVariable String username,
                              @RequestParam int id,
                              @ModelAttribute Note note) {
        service.updateNote(note, id, username);
        return "redirect:/notes/{username}/all_note";
    }

    @GetMapping("/{username}/note")
    public String createNote(@PathVariable String username, Model model) {
        model.addAttribute("note", new Note());
        return "add_note";
    }

    @PostMapping("/{username}/note")
    public String saveNote(@PathVariable String username, @ModelAttribute Note note) {
        service.addNotes(username, note);
        return "redirect:/notes/{username}/all_note";
    }


}