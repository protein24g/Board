package com.study.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class JoinDTO {
    private String userId;
    private String userPw;
    private String userPwCheck;
    private String email;

    @Builder
    public JoinDTO(String userId, String userPw, String userPwCheck, String email){
        this.userId = userId;
        this.userPw = userPw;
        this.userPwCheck = userPwCheck;
        this.email = email;
    }
}
