package org.example;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.io.IOException;

//@Component
public class CaptchaFilter implements Filter {
    CaptchaService captchaService;

    public CaptchaFilter(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        super.doFilter(request, response, chain);
        String captcha = request.getParameter("idCaptcha");
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        //if (authentication != null && authentication.isAuthenticated()) return;
        if (captcha != null && captchaService.validate(((HttpServletRequest) request).getSession(), captcha)){
            chain.doFilter(request, response);
            return;
        } else if (!((HttpServletRequest) request).getRequestURI().equals("/login") && !((HttpServletRequest) request).getRequestURI().equals("/getCaptcha")
        && (authentication == null || !authentication.isAuthenticated())) {
            ((HttpServletResponse) response).sendRedirect("/login");
        } else {
            chain.doFilter(request, response);
        }
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
////        super.doFilter(request, response, chain);
//        String captcha = request.getParameter("idCaptcha");
//        if (captcha != null && captchaService.validate(((HttpServletRequest) request).getSession(), captcha)){
//            chain.doFilter(request, response);
//            return;
//        }
//        else {
//            if (!((HttpServletRequest) request).getRequestURI().equals("/login")) {
//                ((HttpServletResponse) response).sendRedirect("/login");
//            } else {
//                chain.doFilter(request, response);
//            }
//        }
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
