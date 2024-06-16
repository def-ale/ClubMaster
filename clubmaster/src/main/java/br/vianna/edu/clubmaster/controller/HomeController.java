package br.vianna.edu.clubmaster.controller;

import br.vianna.edu.clubmaster.model.User;
import br.vianna.edu.clubmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "home_page";
    }

    @GetMapping("/showHome")
    public String home() {
        return "home_page";
    }

}
