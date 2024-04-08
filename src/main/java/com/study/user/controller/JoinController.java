package com.study.user.controller;

import com.study.user.dto.JoinDTO;
import com.study.user.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String register(@Valid JoinDTO dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getModel()); // 에러 메시지와 에러 관련 데이터 출력
            return "user/join"; // 유효성 검사 실패 시, 회원 가입 페이지로 다시 이동
        }
        joinService.join(dto);
        return "redirect:/user/login";
    }
}
