package com.busanit501.boot_project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("SampleController에서,/hello:  hello");
        model.addAttribute("msg", "Hello World!");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        // 화면에 전달할 샘플 데이터 배열
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd");
        // 서버 -> 화면, 데이터 전달.
        model.addAttribute("list", list);
    }
    
} //SampleController 닫는 블록
