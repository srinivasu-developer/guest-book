package com.guest.book.controllers;

import com.guest.book.entities.User;
import com.guest.book.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * A controller to handle web requests to manage {@link HomeController}s
 *
 * @author Srinivasu Nakka
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/register")
    public String registration(Model model, @ModelAttribute(binding = false) User user) {
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute("user") User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return registration(model, user);
        }
        model.addAttribute("user", user);
        userRepository.save(user);
        return "redirect:/guestbook";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }
}
