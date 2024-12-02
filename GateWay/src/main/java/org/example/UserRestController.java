package org.example;

import entity.CatDTO;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/insertUser")
    public void insertUser(@RequestParam String username, @RequestParam String password, @RequestParam Date date, @RequestParam String email){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User(username, passwordEncoder.encode(password), email);
        userService.insertUser(user);
        userService.addOwner(user, user.getId(), date);
    }


}

//http://localhost:8080/api/users/insertUser?username=Alex&password=1
//"Elix"
//1