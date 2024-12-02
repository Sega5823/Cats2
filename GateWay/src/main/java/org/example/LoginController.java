package org.example;

import org.example.CaptchaService;
import org.example.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    CaptchaService captchaService;
    @Autowired
    UserService userService;
    @GetMapping("/login")
    public String login(){
        return "LoginForm";
    }
    @GetMapping("/main")
    public String main(){
        return "MainPage";
    }

    //http://localhost:8080/login
//    @PostMapping("/perform_login")
//    public ModelAndView performLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//                                     @RequestParam("username") String username, @RequestParam("password") String password,
//                                     @RequestParam("idCaptcha") String idCaptcha){
//        HttpSession httpSession = httpServletRequest.getSession();
//        ModelAndView modelAndView = new ModelAndView();
//        if (!captchaService.validate(httpSession, idCaptcha)){
//            return null;
//        };
//    }
}