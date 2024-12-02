package org.example;

import org.example.PasswordResetTokenRepository;
import org.example.UserRepository;
import org.example.PasswordResetToken;
import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {
//    @Autowired
    private UserRepository userRepository;
    //@Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    //@Autowired
    private PasswordEncoder passwordEncoder;
    private JavaMailSender javaMailSender;

    @Autowired
    public PasswordResetTokenService(UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }
    public void createPasswordToken(User user, String token){
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setDate(LocalDateTime.now().plusHours(24));
        passwordResetToken.setUser(user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
    public void sendMessage(String email){
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new RuntimeException("User is not found by email");
        }
        String token = UUID.randomUUID().toString();
        createPasswordToken(user, token);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("token");
        simpleMailMessage.setFrom("test12983476@mail.ru");
        // ссылка на сброс пароля
        simpleMailMessage.setText("http://localhost:8080/reset/resetPostPassword?token=" + token);
        javaMailSender.send(simpleMailMessage);
    }
    public boolean legitToken(String token){
        PasswordResetToken findedToken = passwordResetTokenRepository.findByToken(token).get();
        if (findedToken != null && LocalDateTime.now().isBefore(findedToken.getDate())){
            return true;
        }
        return false;
    }
    public void savePassword(User user, String password){
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
