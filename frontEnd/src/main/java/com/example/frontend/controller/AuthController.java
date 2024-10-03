package com.example.frontend.controller;

import com.example.frontend.service.ClientService;
import com.example.notes.dto.LoginDto;
import com.example.notes.dto.MyUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto) {

        clientService.login(loginDto);
        return "user_info";
    }

    @GetMapping("/registration")
    public String createUser(Model model) {

        model.addAttribute("newUser", new MyUserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute MyUserDto userDto) {

        clientService.registerUser(userDto);
        return "user_info";
    }

    @GetMapping("/logout")
    public String logout() {

        clientService.logout();
        return "login";
    }

}
