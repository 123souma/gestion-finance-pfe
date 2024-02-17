package com.example.finance.Controller;

import com.example.finance.Service.UserService;
import com.example.finance.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.finance.Interface.UsersRepository;
@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        try {
            User user = usersRepository.findByEmail(email);
            if (user == null) {
                return ResponseEntity.notFound().build(); // Retourne une réponse 404 si aucun utilisateur n'est trouvé
            }
            return ResponseEntity.ok(user); // Retourne l'utilisateur trouvé avec une réponse 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retourne une réponse 500 en cas d'erreur interne
        }
    }


    @PostMapping("/login")
    public String loginUser(@RequestBody User userLoginRequest) {
        // Extraire l'email et le mot de passe de la demande
        String email = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();

        // Vérifier les informations d'identification de l'utilisateur
        if (userService.checkUserCredentials(email, password)) {
            return "Login successful!";
        } else {
            return "Invalid email or password";
        }
    }



}
