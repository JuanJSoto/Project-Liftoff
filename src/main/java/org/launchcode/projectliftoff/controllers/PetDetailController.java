package org.launchcode.projectliftoff.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetDetailController {

    @GetMapping("pet-details")
    public String petDetails(Model model) {
        return "pet-details";
    }

}
