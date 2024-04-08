package com.study.member.controller;

import com.study.member.dto.JoinDTO;
import com.study.member.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "member/join";
    }

    @PostMapping("/joinProc")
    public String register(JoinDTO dto){
        joinService.join(dto);
        return "redirect:/member/login";
    }
}
