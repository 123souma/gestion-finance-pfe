package com.example.finance.Controller;

import com.example.finance.Repository.UserRepository;
import com.example.finance.Service.UserService;
import com.example.finance.entites.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.finance.Interface.UsersRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private UserRepository userRepository;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestParam("file") MultipartFile file, @RequestParam("user") String userJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User user = objectMapper.readValue(userJson, User.class);
            if (userService.emailExists(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
            }
            // Vérifier si tous les champs requis sont présents
            if (user.getNom() == null || user.getPrenom() == null || user.getEmail() == null || user.getPassword() == null || user.getNum_tel() == null || user.getVille() == null || user.getDate_naiss() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required");
            }
            // Enregistrer l'utilisateur dans la base de données
            User savedUser = userRepository.save(user);
            // Récupérer l'ID de l'utilisateur après l'avoir enregistré
            Long userId = savedUser.getCin();
            // Enregistrer la photo de l'utilisateur en utilisant son ID
            String photoUrl = saveUserPhoto(file, userId);
            // Définir l'URL de la photo dans l'objet User
            savedUser.setPhotoUrl(photoUrl);
            // Enregistrer à nouveau l'utilisateur pour mettre à jour l'URL de la photo
            userRepository.save(savedUser);
            return ResponseEntity.ok(savedUser);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process user data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
    }

    private String saveUserPhoto(MultipartFile file, Long userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            throw new IllegalArgumentException("Invalid file name");
        }
        // Générer un nom de fichier unique en fonction de l'ID de l'utilisateur et du nom du fichier
        String uniqueFileName = userId + "_" + System.currentTimeMillis() + "_" + fileName;
        // Construire l'URL complète en utilisant le chemin absolu du répertoire d'upload et le nom de fichier unique
        String uploadDir = "C:/Users/rabhi/Downloads"; // Remplacez ceci par le chemin absolu de votre répertoire d'upload
        String photoUrl = uploadDir + "/" + uniqueFileName;

        // Enregistrer le fichier dans le répertoire d'upload avec le nom de fichier unique
        FileUploadUtil.saveFile(uploadDir, uniqueFileName, file);

        // Retourner l'URL complète de l'image
        return photoUrl;
    }



    private byte[] loadUserPhotoContent(String photoUrl) throws IOException {
        // Construire le chemin d'accès complet en utilisant le répertoire d'upload et le nom du fichier
        String uploadDir = "C:/Users/rabhi/Downloads"; // Assurez-vous que c'est le bon répertoire
        Path imagePath = Paths.get(photoUrl);
        // Vérifier si le fichier existe avant de tenter de le charger
        if (!Files.exists(imagePath)) {
            throw new FileNotFoundException("File not found: " + imagePath.toString());
        }
        // Charger le contenu de l'image à partir du chemin spécifié
        return Files.readAllBytes(imagePath);
    }

    @GetMapping("/photo/{email}")
    public ResponseEntity<Resource> getUserPhotoByEmail(@PathVariable String email) {
        try {
            User user = usersRepository.findByEmail(email);
            if (user == null || user.getPhotoUrl() == null) {
                return ResponseEntity.notFound().build();
            }
            // Charger le contenu de l'image à partir de l'URL de la photo de l'utilisateur
            byte[] imageContent = loadUserPhotoContent(user.getPhotoUrl());
            // Créer une ressource ByteArrayResource à partir du contenu de l'image
            Resource imageResource = new ByteArrayResource(imageContent);
            // Retourner une réponse avec le contenu de l'image et les en-têtes appropriés
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + user.getPhotoUrl() + "\"")
                    .body(imageResource);
        } catch (Exception e) {
            // En cas d'erreur, retourner une réponse avec un statut d'erreur interne du serveur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/users/info/{email}")
    public ResponseEntity<User> getUserInfoByEmail(@PathVariable String email) {
        try {
            User userInfo = userService.getUserInfoByEmail(email);

            if (userInfo == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/cin/{cin}")
    public ResponseEntity<Resource> getUserPhotoByCin(@PathVariable Long cin) {
        try {
            User user = usersRepository.findByCin(cin);
            if (user == null || user.getPhotoUrl() == null) {
                return ResponseEntity.notFound().build();
            }
            // Charger le contenu de l'image à partir de l'URL de la photo de l'utilisateur
            byte[] imageContent = loadUserPhotoContent(user.getPhotoUrl());
            // Créer une ressource ByteArrayResource à partir du contenu de l'image
            Resource imageResource = new ByteArrayResource(imageContent);
            // Retourner une réponse avec le contenu de l'image et les en-têtes appropriés
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + user.getPhotoUrl() + "\"")
                    .body(imageResource);
        } catch (Exception e) {
            // En cas d'erreur, retourner une réponse avec un statut d'erreur interne du serveur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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









    @GetMapping("/ids")
    public List<Long> getUserIds() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::getCin).collect(Collectors.toList());
    }


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        return userService.updateUser(userId, userDetails);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
