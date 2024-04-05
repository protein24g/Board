package com.study.member.dto;

import com.study.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> member.getRole());
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // Member 객체에서 비밀번호 정보를 반환합니다.
        return member.getUserpw();
    }

    @Override
    public String getUsername() {
        // Member 객체에서 사용자명을 반환합니다.
        return member.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부를 반환합니다. 여기서는 단순화를 위해 true를 반환합니다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠김 여부를 반환합니다. 여기서는 단순화를 위해 true를 반환합니다.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명 만료 여부를 반환합니다. 여기서는 단순화를 위해 true를 반환합니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부를 반환합니다. 실제로는 Member 객체의 상태에 따라 결정되어야 합니다.
        return true;
    }
}
