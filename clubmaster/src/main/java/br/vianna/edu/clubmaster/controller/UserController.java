package br.vianna.edu.clubmaster.controller;

import br.vianna.edu.clubmaster.model.User;
import br.vianna.edu.clubmaster.repository.UserRepository;
import br.vianna.edu.clubmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;
    private UserRepository ur;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register_page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login_page";
    }
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("user") User user, Model model) {

        User existingUser = ur.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "redirect:/showHome";
        } else {
            model.addAttribute("error", "Email ou senha incorretos. Por favor, tente novamente.");
            return "login_page";
        }
    }
}
