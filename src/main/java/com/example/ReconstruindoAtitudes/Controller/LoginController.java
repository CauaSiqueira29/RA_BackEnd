package com.example.ReconstruindoAtitudes.Controller;

import com.example.ReconstruindoAtitudes.Model.LoginModel;
import com.example.ReconstruindoAtitudes.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestBody LoginModel dados) {
        boolean isAuthenticated = loginService.authenticate(dados.getCnpj(), dados.getSenha());
        if (isAuthenticated) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }
}
