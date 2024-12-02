package org.example;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class CaptchaController {
    @Autowired
    CaptchaService captchaService;

    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpSession httpSession, HttpServletResponse httpServletResponse){

        String id = captchaService.generate(httpSession);
        String text = captchaService.getTextByID(id);
        byte[] image = captchaService.generatePicture(text);
        httpServletResponse.setContentType("image/jpeg");
        try {
            httpServletResponse.getOutputStream().write(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
