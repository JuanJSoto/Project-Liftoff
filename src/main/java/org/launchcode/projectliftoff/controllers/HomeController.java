package org.launchcode.projectliftoff.controllers;

import org.launchcode.projectliftoff.models.data.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.HashMap;

@Controller
public class HomeController {

//    @Autowired
//    private PetRepository petRepository;

    @GetMapping("")
    public String home(Model model) {
        return "home";
    }

}
