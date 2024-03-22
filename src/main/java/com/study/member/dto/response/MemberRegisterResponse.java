package com.study.member.dto.response;
import com.study.member.entity.Member;
import lombok.*;

@Getter
@Setter
@ToString
public class MemberRegisterResponse {
    private String userid;
    private String email;

    @Builder
    public MemberRegisterResponse(Integer id, String userid, String email){
        this.userid = userid;
        this.email = email;
    }

    public static MemberRegisterResponse toDto(Member member){
        return MemberRegisterResponse.builder()
                .userid(member.getUserid())
                .email(member.getEmail())
                .build();
    }
}
