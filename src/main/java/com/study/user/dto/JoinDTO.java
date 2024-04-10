package com.study.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@ToString
public class JoinDTO {
    @NotBlank(message = "사용자 ID는 필수 입력 값입니다.")
    @Size(min = 3, max = 12, message = "사용자 ID는 3자 이상, 12자 이하로 설정해야 합니다.")
    private String userId;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 2, max = 8, message = "닉네임은 2자 이상, 8자 이하로 설정해야 합니다.")
    private String nickName;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "비밀번호는 8자 이상, 15자 이하로 설정해야 합니다.")
    private String userPw;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String userPwCheck;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Builder
    public JoinDTO(String userId, String nickName, String userPw, String userPwCheck, String email){
        this.userId = userId;
        this.nickName = nickName;
        this.userPw = userPw;
        this.userPwCheck = userPwCheck;
        this.email = email;
    }

    // 비밀번호 일치 여부 검증
    public boolean isPasswordEqual() {
        return this.userPw != null && this.userPw.equals(this.userPwCheck);
    }
}
