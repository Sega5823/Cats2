package org.example;

import org.example.PasswordResetTokenRepository;
import org.example.PasswordResetToken;
import org.example.User;
import org.example.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/reset")
public class PasswordResetTokenController {
    @Autowired
    PasswordResetTokenService passwordResetTokenService;
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam(name = "email") String email){
        try {
            passwordResetTokenService.sendMessage(email);
        } catch (Exception e){
            return "EmailError";
        }
        return "MessageIsSended";
    }
    //http://localhost:8080/reset/resetPassword?email=sega9019@gmail.com
    //http://localhost:8080/reset/resetPostPassword?email=sega9019@gmail.com
    //http://localhost:8080/reset/resetPassword?token=dd8030fb-326a-450a-b152-1b2767aa39bf

    @GetMapping("/resetPostPassword")
    public String legitToken(@RequestParam(name = "token") String token, Model model){
        if (passwordResetTokenService.legitToken(token)){
            model.addAttribute("token", token);
            return "resetPassword";
        }
        return "ErrorLink";
        //templates

    }
    @GetMapping("/resetPass")
    public String resetPass(){
        return "resetPasswordByEmail";
    }

    //http://localhost:8080/reset/resetPostPassword?token=89312ad8-5885-4aca-923f-3ef2a1e52d66

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam(name = "token") String token, @RequestParam (name = "pass") String password, Model model){
        Optional<PasswordResetToken> userOptional = passwordResetTokenRepository.findByToken(token);
        if (userOptional.isEmpty()){
            return "ErrorLink";
        }
        User user = userOptional.get().getUser();
        if (user != null){
            passwordResetTokenService.savePassword(user, password);
            return "PasswordIsSaved";
        } else {
            return "UserNull";
        }
    }
    //http://localhost:8080/reset/resetPostPassword?token=89312ad8-5885-4aca-923f-3ef2a1e52d66
}
