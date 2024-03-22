package com.study.member.dto.requests;

import com.study.member.entity.Member;
import com.study.common.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberRegisterRequest {
    private String userid;
    private String userpw;
    private String userpwCheck;
    private String email;

    @Builder
    public MemberRegisterRequest(String userid, String userpw, String userpwCheck, String email){
        this.userid = userid;
        this.userpw = userpw;
        this.userpwCheck = userpwCheck;
        this.email = email;
    }

    public static Member toEntity(MemberRegisterRequest dto){
        return Member.builder()
                .userid(dto.getUserid())
                .userpw(dto.getUserpw())
                .email(dto.getEmail())
                .role(Role.USER)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
