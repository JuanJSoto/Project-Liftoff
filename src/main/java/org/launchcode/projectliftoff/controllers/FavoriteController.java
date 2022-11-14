package org.launchcode.projectliftoff.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FavoriteController {

    @GetMapping("favorites")
    public String favorites(Model model) {
        return "favorites";
    }

}
