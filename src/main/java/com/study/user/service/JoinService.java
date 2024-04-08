package com.study.user.service;

import com.study.user.dto.JoinDTO;
import com.study.user.entity.User;
import com.study.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void join(JoinDTO dto) {
        // 비밀번호 일치 여부 검증
        if (!dto.isPasswordEqual()) {
            System.out.println("비밀번호가 null이거나 비밀번호 확인과 일치하지 않습니다.");
        }else{
            User user = userRepository.findByUserId(dto.getUserId()).orElse(null);
            // 해당 사용자 ID로 등록된 회원이 없는 경우에만 회원가입 진행
            if (user == null) {
                User target = User.builder()
                        .userId(dto.getUserId())
                        .nickName(dto.getNickName())
                        .userPw(passwordEncoder.encode(dto.getUserPw())) // 비밀번호 인코딩
                        .email(dto.getEmail())
                        .role("ROLE_USER")
                        .createdDate(LocalDateTime.now())
                        .build();
                userRepository.save(target);
            }else {
                // 비밀번호가 null이거나, 비밀번호 확인 값과 일치하지 않는 경우 처리
                // 예: 로그 출력, 사용자에게 에러 메시지 전달 등
                System.out.println("이미 등록된 아이디 입니다.");
            }
        }

    }
}
