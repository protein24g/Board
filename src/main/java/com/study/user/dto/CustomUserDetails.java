package com.study.user.dto;

import com.study.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRole());
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // Member 객체에서 비밀번호 정보를 반환합니다.
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        // User 객체에서 사용자명을 반환합니다.
        return user.getUserId();
    }

    // 사용자 닉네임을 반환하는 메서드 추가
    public String getNickName() {
        return user.getNickName();
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
