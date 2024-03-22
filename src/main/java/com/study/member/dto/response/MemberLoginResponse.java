package com.study.member.dto.response;

import com.study.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberLoginResponse {
    private String userid;

    @Builder
    public MemberLoginResponse(String userid){
        this.userid = userid;
    }

    public static MemberLoginResponse toDto(Member member){
        return MemberLoginResponse.builder()
                .userid(member.getUserid())
                .build();
    }
}
