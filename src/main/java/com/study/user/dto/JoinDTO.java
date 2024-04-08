package com.study.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class JoinDTO {
    private String userId;
    private String nickName;
    private String userPw;
    private String userPwCheck;
    private String email;

    @Builder
    public JoinDTO(String userId, String nickName, String userPw, String userPwCheck, String email){
        this.userId = userId;
        this.nickName = nickName;
        this.userPw = userPw;
        this.userPwCheck = userPwCheck;
        this.email = email;
    }
}
