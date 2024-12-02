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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/getAllCats")
    public String findOwnerByID(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var Cats = userService.findCats(user.getId());
        List<CatDTO> catDTOList = null;
        try {
            catDTOList = Cats.get(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            //return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
        model.addAttribute("cats", catDTOList);
        //return ResponseEntity.status(HttpStatus.OK).body(catDTOList);
        return "AllCats";
    }

    @PostMapping("/deleteCat")
    public String deleteCat(@RequestParam int id) {
        userService.deleteCat(id);
        return "redirect:/users/getAllCats";
    }

    @PostMapping("/addFriend")
    public String addFriend(@RequestParam(name = "cat") int cat, @RequestParam(name = "cat2") int cat2) {
        userService.addFriend(cat, cat2);
        return "redirect:/users/getAllCats";
    }

}

//http://localhost:8080/api/users/insertUser?username=Alex&password=1
//"Elix"
//1