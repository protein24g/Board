package com.study.anonyboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnonyBoardController {
    @GetMapping("anonyboard/list")
    public String listP(Model model){
        model.addAttribute("currentPage", "anonyboard");
        return "anonyboard/list";
    }
}
