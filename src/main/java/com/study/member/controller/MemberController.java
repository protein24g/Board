package com.study.member.controller;

import com.study.member.dto.requests.MemberLoginRequest;
import com.study.member.dto.requests.MemberRegisterRequest;
import com.study.member.dto.response.MemberLoginResponse;
import com.study.member.dto.response.MemberRegisterResponse;
import com.study.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String hi(){
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<MemberRegisterResponse> register(@RequestBody MemberRegisterRequest dto){
        MemberRegisterResponse memberRegisterResponse = memberService.register(dto);
        return (memberRegisterResponse != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memberRegisterResponse) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequest dto){
        MemberLoginResponse memberLoginResponse = memberService.login(dto);
        return (memberLoginResponse != null) ?
                ResponseEntity.status(HttpStatus.OK).body(memberLoginResponse) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
