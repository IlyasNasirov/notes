package com.example.frontend.service;

import com.example.notes.dto.LoginDto;
import com.example.notes.dto.MyUserDto;
import com.example.notes.dto.NoteDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service

public class ClientServiceImpl implements ClientService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ClientServiceImpl(RestTemplate restTemplate, @Value("${base.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public List<NoteDto> getAllNotes(String username) {
        String url = baseUrl + "/users/{username}/notes";

        ResponseEntity<List<NoteDto>> responseEntity = restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                }, username);
        return responseEntity.getBody();
    }

    @Override
    public NoteDto getNoteById(String username, int noteId) {
        String url = baseUrl + "/users/{username}/notes/{noteId}";

        ResponseEntity<NoteDto> responseEntity = restTemplate.getForEntity(url, NoteDto.class, username, noteId);
        return responseEntity.getBody();
    }

    @Override
    public void addNote(String username, NoteDto noteDto) {
        String url = baseUrl + "/users/{username}/notes";
        restTemplate.postForEntity(url, noteDto, Void.class);
    }

    @Override
    public void deleteNoteById(String username, int noteId) {
        String url = baseUrl + "/users/{username}/notes";
        String urlWithParam= UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("noteId", noteId)
                .buildAndExpand(username)
                .toUriString();

        restTemplate.exchange(urlWithParam, HttpMethod.DELETE, null, Void.class);
    }

    @Override
    public NoteDto updateNote(String username, int noteId, NoteDto noteDto) {
        return null;
    }

    @Override
    public MyUserDto login(LoginDto loginDto) {
        return null;
    }

    @Override
    public MyUserDto registerUser(MyUserDto userDto) {
        return null;
    }

    @Override
    public void logout() {

    }
}
