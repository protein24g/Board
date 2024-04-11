package com.study.user.service;

import com.study.user.dto.CustomUserDetails;
import com.study.user.entity.User;
import com.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId).orElse(null);
        System.out.println(user.toString());
        System.out.println("Asdasdasdas");
        if(user != null){
            return new CustomUserDetails(user);
        }
        return null;
    }
}
