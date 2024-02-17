package com.example.finance.Service;

import com.example.finance.Repository.UserRepository;
import com.example.finance.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        // Vérifier si l'email existe déjà
        if (emailExists(user.getEmail())) {
            // Gérer le cas où l'email existe déjà
            // Vous pouvez lever une exception, renvoyer un message d'erreur, etc.
            throw new IllegalArgumentException("Email already exists");
        }

        // Crypter le mot de passe avant de l'ajouter à la base de données
        return userRepository.save(user);

    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean checkUserCredentials(String email, String password) {
        // Rechercher l'utilisateur par email
        User existingUser = userRepository.findByEmail(email);

        // Vérifier si l'utilisateur existe et si le mot de passe correspond
        return existingUser != null && existingUser.getPassword().equals(password);
    }
}
