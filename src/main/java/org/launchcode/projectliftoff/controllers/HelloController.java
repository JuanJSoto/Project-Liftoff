package org.launchcode.projectliftoff.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping(value = "")
    @ResponseBody //returns plain text, remove after adding templates
    public String index() {
        return "Hello, world!";
    }
}
