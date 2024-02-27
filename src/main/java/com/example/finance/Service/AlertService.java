package com.example.finance.Service;

import com.example.finance.Repository.AlertRepository;
import com.example.finance.Repository.UserRepository;
import com.example.finance.entites.Alert;
import com.example.finance.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailAlert(User user, Alert alert) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(user.getEmail());
            helper.setSubject("Nouvelle alerte: " + alert.getType());

            String htmlContent = "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; }"
                    + ".alert { background-color: #f2f2f2; border-left: 6px solid #2196F3; padding: 10px; margin-bottom: 10px; }"
                    + ".alert h3 { color: #2196F3; }"
                    + "</style></head><body>"
                    + "<div class='alert'>"
                    + "<h3>Bonjour " + user.getNom() + ",</h3>"
                    + "<p>Une nouvelle alerte de type '" + alert.getType() + "' a été générée le " + alert.getDate() + ".</p>"
                    + "<p>Description de l'alerte: " + alert.getDescription() + "</p>"
                    + "<p>Statut de l'alerte: " + alert.getStatus() + "</p>"
                    + "<p>Contactez-nous s'il vous plaît au numéro de téléphone (+216) 50898586.</p>"
                    + "</div></body></html>";

            helper.setText(htmlContent, true);

            emailSender.send(message);
        } catch (MessagingException e) {
            // Gérer l'exception ici
            e.printStackTrace();
        }
    }

    public List<Alert> getAlertsByUserCin(Long cin) {
        return alertRepository.findByUserCin(cin);
    }

    public void generateAlert(Long userId, String type, Date date, String description, String status) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }

        Alert alert = new Alert();
        alert.setUser(user);
        alert.setType(type);
        alert.setDate(date);
        alert.setDescription(description);
        alert.setStatus(status);

        alertRepository.save(alert);

        // Envoi de l'e-mail à l'utilisateur après avoir sauvegardé l'alerte
        sendEmailAlert(user, alert);
    }
}
