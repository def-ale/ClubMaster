package br.vianna.edu.clubmaster.config;

import br.vianna.edu.clubmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/", "/negado", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
        ).formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
        ).logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
        ).exceptionHandling((ex) -> ex
                .accessDeniedPage("/negado")
        );
        return http.build();
    }
}
