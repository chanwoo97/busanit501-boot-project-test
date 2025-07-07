package com.busanit501.boot_project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("SampleController에서,/hello:  hello");
        model.addAttribute("msg", "Hello World!");

    }
}
