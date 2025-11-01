package com.example.imc.controller;

import com.example.imc.model.User;
import com.example.imc.model.ImcRecord;
import com.example.imc.service.UserService;
import com.example.imc.service.ImcService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class WebController {
    private final UserService userService;
    private final ImcService imcService;

    public WebController(UserService userService, ImcService imcService){
        this.userService = userService;
        this.imcService = imcService;
    }

    @GetMapping("/")
    public String home() { return "login"; }

    @GetMapping("/register")
    public String showRegister(Model m){
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model){
        try {
            userService.register(user);
            model.addAttribute("message","Registro exitoso. Inicia sesión.");
            return "login";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("user", user);
            return "register";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model){
        Optional<User> opt = userService.authenticate(username, password);
        if (opt.isPresent()){
            session.setAttribute("loggedUser", opt.get());
            return "redirect:/imc";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/imc")
    public String imcForm(HttpSession session, Model model){
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            model.addAttribute("error", "Debes iniciar sesión para calcular IMC");
            return "login";
        }
        model.addAttribute("user", user);
        return "imc_form";
    }

    @PostMapping("/imc/calc")
    public String calculateImc(@RequestParam Double peso, HttpSession session, Model model){
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            model.addAttribute("error", "Debes iniciar sesión");
            return "login";
        }
        if (peso == null || peso <= 0) {
            model.addAttribute("error", "Peso inválido");
            model.addAttribute("user", user);
            return "imc_form";
        }
        ImcRecord r = imcService.saveImcRecord(user, peso);
        model.addAttribute("current", r);
        List<ImcRecord> history = imcService.getHistory(user);
        model.addAttribute("history", history);
        return "history";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}