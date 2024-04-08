package com.study.member.service;

import com.study.member.dto.JoinDTO;
import com.study.member.entity.Member;
import com.study.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    public void join(JoinDTO dto) {
        // 비밀번호가 null이 아니며, 비밀번호 확인 값과 일치하는지 체크
        if (dto.getUserPw() != null && dto.getUserPw().equals(dto.getUserPwCheck())) {
            Member member = memberRepository.findByUserid(dto.getUserId());
            // 해당 사용자 ID로 등록된 회원이 없는 경우에만 회원가입 진행
            if (member == null) {
                Member target = Member.builder()
                        .userid(dto.getUserId())
                        .userpw(passwordEncoder.encode(dto.getUserPw())) // 비밀번호 인코딩
                        .email(dto.getEmail())
                        .role("ROLE_USER")
                        .createdDate(LocalDateTime.now())
                        .build();
                memberRepository.save(target);
            }
        } else {
            // 비밀번호가 null이거나, 비밀번호 확인 값과 일치하지 않는 경우 처리
            // 예: 로그 출력, 사용자에게 에러 메시지 전달 등
            System.out.println("비밀번호가 null이거나 비밀번호 확인과 일치하지 않습니다.");
        }
    }
}
