package com.study.user.controller;

import com.study.user.dto.JoinDTO;
import com.study.user.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "user/join";
    }

    @PostMapping("/joinProc")
    public String register(JoinDTO dto){
        joinService.join(dto);
        return "redirect:/user/login";
    }
}
