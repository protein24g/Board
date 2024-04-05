package com.study.member.dto.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginRequest{
    private String userid;
    private String userpw;

    @Builder
    public MemberLoginRequest(String userid, String userpw){
        this.userid = userid;
        this.userpw = userpw;
    }
}
