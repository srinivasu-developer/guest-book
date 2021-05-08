package com.guest.book.controllers;

import com.guest.book.entities.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * A controller to handle web requests to manage {@link HomeController}s
 *
 * @author Srinivasu Nakka
 */
@Slf4j
@Controller
public class HomeController {

    /**
     * The home handler
     *
     * @return model and view of the homepage
     */
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home", "newEntry", new Entry());
    }
}
