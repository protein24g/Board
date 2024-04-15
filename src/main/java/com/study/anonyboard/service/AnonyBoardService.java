package com.study.anonyboard.service;

import com.study.anonyboard.dto.requests.AnonyBoardRequest;
import com.study.anonyboard.dto.response.AnonyBoardResponse;
import com.study.anonyboard.entity.AnonyBoard;
import com.study.anonyboard.repository.AnonyBoardRepository;
import com.study.board.dto.response.BoardResponse;
import com.study.board.entity.Board;
import com.study.user.dto.CustomUserDetails;
import com.study.user.entity.User;
import com.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnonyBoardService {
    private final AnonyBoardRepository anonyBoardRepository;
    private final UserRepository userRepository;

    public Page<AnonyBoardResponse> getList(Integer page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<AnonyBoard> boards = anonyBoardRepository.findAll(pageable);

        Page<AnonyBoardResponse> anonyBoards = boards
                .map(board -> AnonyBoardResponse.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createdDate(board.getCreatedDate())
                        .build());
        return anonyBoards;
    }

    public boolean isWriter(Integer userId, Integer postId) {
        AnonyBoard post = anonyBoardRepository.findById(postId).orElse(null);
        if(post == null || post.getUser() == null) return false;
        return post.getUser().getId().equals(userId);
    }

    public void write(AnonyBoardRequest dto) {
        // 현재 인증된 사용자의 Authentication 객체를 얻음
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userRepository.findByUserId(customUserDetails.getUsername()).orElse(null);
            if (user != null){
                anonyBoardRepository.save(AnonyBoard.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .view(0)
                        .user(user)
                        .createdDate(LocalDateTime.now())
                        .build());
            }
        }
    }

    public AnonyBoardResponse findById(Integer id){
        AnonyBoard anonyBoard = anonyBoardRepository.findById(id).orElse(null);
        return AnonyBoardResponse.builder()
                .id(anonyBoard.getId())
                .title(anonyBoard.getTitle())
                .content(anonyBoard.getContent())
                .createdDate(anonyBoard.getCreatedDate())
                .build();
    }
}
