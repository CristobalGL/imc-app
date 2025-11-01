package com.example.imc.controller;

import com.example.imc.model.ImcRecord;
import com.example.imc.model.User;
import com.example.imc.service.ImcService;
import com.example.imc.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestImcController {
    private final UserService userService;
    private final ImcService imcService;
    public RestImcController(UserService userService, ImcService imcService){
        this.userService = userService;
        this.imcService = imcService;
    }

    // El endpoint REST /api/users/{username}/history devuelve el histórico y es el que se va a desplegar en railweb.app como servicio.
    @GetMapping("/users/{username}/history")
    public List<ImcRecord> getHistory(@PathVariable String username){
        Optional<User> opt = userService.findByUsername(username);
        if (opt.isEmpty()) throw new RuntimeException("Usuario no encontrado");
        return imcService.getHistory(opt.get());
    }
}