package com.example.finance.Controller;

import com.example.finance.Exception.UserNotFoundException;
import com.example.finance.Service.UserServices;


import com.example.finance.entites.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.sasl.SaslServer;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;

@Controller
@RequestMapping("")
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserServices customerService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {

        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(@RequestBody Map<String, String> requestBody, Model model, HttpServletRequest request) {
        String email = requestBody.get("email");
        String token = RandomString.make(30);
        try {
            customerService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        model.addAttribute("pagetitre", "forget password");
        return "forgot_password";
    }


    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
       helper.setFrom("rabhisouma89@gmail.com", "Shopme Support");
      helper.setTo(email);
      String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";
                helper.setSubject(subject);
                helper.setText(content,true);
                mailSender.send(message);
    }



    @GetMapping("/forget/test")
    @ResponseBody
    public ResponseEntity<User> testGetByResetPasswordToken(@RequestParam("token") String token) {
        User customer = customerService.getByResetPasswordToken(token);
        if (customer != null) {
            System.out.println();
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @GetMapping("/reset_password")
    @ResponseBody
    public ResponseEntity<String> showResetPasswordForm(@RequestParam("token") String token, Model model) {
        try {
            User customer = customerService.getByResetPasswordToken(token);
            model.addAttribute("token", token);

            if (customer == null) {
                return ResponseEntity.badRequest().body("token_invalid");
            }
            // Construit l'URL de redirection vers le composant React
            String resetPasswordUrl = "http://localhost:3000/admin/pages?token=" + token;
            // Redirige vers le composant React
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(resetPasswordUrl));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } catch (Exception e) {
            model.addAttribute("message", "Error occurred while processing your request. Please try again later.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("message11");
        }
    }


    @PostMapping("/reset_password")
    @ResponseBody // Cette annotation indique à Spring de renvoyer directement la réponse comme corps de la réponse HTTP
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        User customer = customerService.getByResetPasswordToken(token);
        if (customer == null) {
            return "Invalid Token";
        } else {
            customerService.updatePassword(customer, password);
            return "You have successfully changed your password.";
        }
    }


    @PostMapping("/modifierr_password")
    @ResponseBody
    public String modifierPassword(HttpServletRequest request, @RequestParam String token, @RequestParam String password) {
        User customer = customerService.getByResetPasswordToken(token);
        if (customer == null) {
            return "malheureusement";
        } else {
            try {
                return "bien modifié";
            } catch (Exception e) {
                e.printStackTrace();
                return "malheureusement";
            }
        }
    }
}