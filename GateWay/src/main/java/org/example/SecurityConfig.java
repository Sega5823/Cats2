package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Security;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    UserService userService;
//    @Autowired
//    CaptchaFilter captchaFilter;



//    @Bean
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeRequests()
//                .antMatchers("/api/users/insertUser/**")
//                .hasRole(Role.ADMIN.name())
//                .antMatchers("/api/owners/insertOwner/**").hasRole(Role.ADMIN.name())
//                .antMatchers("/api/owners/deleteOwner/**").hasRole(Role.ADMIN.name())
//                .antMatchers("/api/reset/**").permitAll()
//                .antMatchers("/getCaptcha").permitAll()
//                .anyRequest().permitAll()
//                .and().formLogin()
//                .loginPage("/login")
//                    .loginProcessingUrl("/perform_login")
//                    .defaultSuccessUrl("/main")
//                    .failureUrl("/login")
//                .permitAll().and().httpBasic()
//                .and().logout()
//                    .logoutUrl("/perform_logout")
//                    .deleteCookies("JSESSIONID")
//                    .logoutSuccessUrl("/login")
//                .permitAll()
//                .and().addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
//        ;
//        //return httpSecurity.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeHttpRequests((x)-> x.requestMatchers("/api/users/insertUser/**")
                .hasRole(Role.ADMIN.name())
                .requestMatchers("/api/owners/insertOwner/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/api/owners/deleteOwner/**").hasRole(Role.ADMIN.name())
                .requestMatchers("/api/reset/**").permitAll()
                .requestMatchers("/getCaptcha").permitAll()
                .anyRequest().permitAll())
                .formLogin((x)-> x
                .loginPage("/login")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/main")
                    .failureUrl("/login")
                .permitAll())
                        //.httpBasic())
                .logout((x)-> x
                    .logoutUrl("/perform_logout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login")
                .permitAll())
                        //.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                ;
        return httpSecurity.build();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder.inMemoryAuthentication()
//                .withUser("s").password(passwordEncoder().encode("s"))
//                .roles("ADMIN");
////                .antMatchers("/api/users/insertUser/**")
////                .hasRole(Role.ADMIN.name())
////                .antMatchers("/api/owners/insertOwner/**").hasRole(Role.ADMIN.name())
////                .antMatchers("/api/owners/deleteOwner/**").hasRole(Role.ADMIN.name())
////                .anyRequest().authenticated()
////                .and().formLogin().permitAll()
////                .and().logout().permitAll();
//        //return httpSecurity.build();
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

}
