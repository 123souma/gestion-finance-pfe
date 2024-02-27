package com.example.finance.Controller;

import com.example.finance.Service.AlertService;
import com.example.finance.entites.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Alert>> getUserAlerts(@PathVariable Long userId) {
        List<Alert> alerts = alertService.getAlertsByUserCin(userId);
        return ResponseEntity.ok(alerts);
    }

    @PostMapping("/generate")
    public ResponseEntity<Void> generateAlert(@RequestBody Alert alertRequest) {
        Long userId = alertRequest.getUser() != null ? alertRequest.getUser().getCin() : null;
        System.out.println("UserId : " + userId);
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            alertService.generateAlert(userId, alertRequest.getType(), alertRequest.getDate(), alertRequest.getDescription(), alertRequest.getStatus());
            return ResponseEntity.ok().build(); // Réponse OK si tout se passe bien
        } catch (Exception e) {
            // Gestion de l'exception
            e.printStackTrace(); // Affichage de l'erreur (vous pouvez choisir un meilleur traitement en fonction de votre application)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Réponse d'erreur 500 en cas d'exception
        }
    }
}
