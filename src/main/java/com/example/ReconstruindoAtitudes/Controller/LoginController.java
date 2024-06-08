package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.DTOs.LoginPostDTO;
import com.example.ReconstruindoAtitudes.services.LoginService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginPostDTO dados) {
        boolean isAuthenticated = loginService.authenticate(dados.cnpj(), dados.senha());
        Map<String, String> response = new HashMap<>();
        if (isAuthenticated) {
            response.put("message", "Usuário autenticado");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Usuário não autenticado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}