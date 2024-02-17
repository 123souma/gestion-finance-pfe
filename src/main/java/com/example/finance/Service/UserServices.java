package com.example.finance.Service;

import com.example.finance.Exception.UserNotFoundException;
import com.example.finance.Interface.UsersRepository;
import com.example.finance.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServices {

    @Autowired
    private UsersRepository customerRepo;


    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User customer = customerRepo.findByEmail(email);
        if (customer != null) {
            customer.setPasswordToken(token);
            customerRepo.save(customer);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return customerRepo.findByPasswordToken(token);
    }


    public boolean updatePassword(User customer, String newPassword) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(newPassword);
            customer.setPassword(encodedPassword);
            customer.setPasswordToken(null);
            customerRepo.save(customer);
            return true; // Indique que le mot de passe a été modifié avec succès
        } catch (Exception e) {
            // Gérer toute exception et afficher un message d'erreur si nécessaire
            return false; // Indique que la modification du mot de passe a échoué
        }
    }



}