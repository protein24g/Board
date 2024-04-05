package com.study.member.service;

import com.study.member.dto.requests.MemberLoginRequest;
import com.study.member.dto.requests.MemberRegisterRequest;
import com.study.member.dto.response.MemberLoginResponse;
import com.study.member.dto.response.MemberRegisterResponse;
import com.study.member.entity.Member;
import com.study.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberRegisterResponse register(MemberRegisterRequest dto) {
        if(dto.getUserpw().equals(dto.getUserpwCheck())){
            String encodePw = passwordEncoder.encode(dto.getUserpw());
            dto.setUserpw(encodePw);
            Member saved = memberRepository.save(MemberRegisterRequest.toEntity(dto));
            return MemberRegisterResponse.toDto(saved);
        }
        return null;
    }

    public MemberLoginResponse login(MemberLoginRequest dto) {
        Member target = memberRepository.findByUserid(dto.getUserid());

        if(target != null){
            if (!passwordEncoder.matches(dto.getUserpw(), target.getUserpw()))
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            return MemberLoginResponse.toDto(target);
        }
        return null;
    }
}
