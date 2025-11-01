package com.example.imc.service;

import org.springframework.stereotype.Service;
import com.example.imc.repository.UserRepository;
import com.example.imc.model.User;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) { this.userRepo = userRepo; }

    public User register(User u) throws IllegalArgumentException {
        // Validations
        if (u.getHeight() == null || u.getHeight() < 1.0 || u.getHeight() > 2.5)
            throw new IllegalArgumentException("Estatura inválida: debe ser entre 1.0 y 2.5 m");
        if (u.getAge() == null || u.getAge() < 15)
            throw new IllegalArgumentException("Edad inválida: mínimo 15 años");
        if (u.getUsername() == null || u.getUsername().isBlank())
            throw new IllegalArgumentException("Nombre de usuario inválido");
        if (userRepo.findByUsername(u.getUsername()).isPresent())
            throw new IllegalArgumentException("Nombre de usuario ya existe");
        // NOTE: store plain password for demo only. Hash in real app.
        return userRepo.save(u);
    }

    public Optional<User> authenticate(String username, String password) {
        Optional<User> opt = userRepo.findByUsername(username);
        if (opt.isPresent() && opt.get().getPassword().equals(password)) {
            return opt;
        }
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username){
        return userRepo.findByUsername(username);
    }
}