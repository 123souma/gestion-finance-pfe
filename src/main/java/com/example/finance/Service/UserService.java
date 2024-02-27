package com.example.finance.Service;

import com.example.finance.Repository.UserRepository;
import com.example.finance.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User getUserInfoByEmail(String email) {
        // Utilisez le repository pour rechercher l'utilisateur par email dans la base de données
        return userRepository.findByEmail(email);
    }

    public User addUser(User user) {
        if (emailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
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


// tjibli les users lkoul
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
// par id le user
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Update operation
    public User updateUser(Long userId, User userDetails) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Update all fields
            user.setNom(userDetails.getNom());
            user.setPrenom(userDetails.getPrenom());
            user.setDate_naiss(userDetails.getDate_naiss());
            user.setEmail(userDetails.getEmail());
            user.setNum_tel(userDetails.getNum_tel());
            user.setPassword(userDetails.getPassword());
            user.setVille(userDetails.getVille());
            user.setPhotoUrl(userDetails.getPhotoUrl());
            user.setPasswordToken(userDetails.getPasswordToken());

            // Save the updated user
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }}
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
