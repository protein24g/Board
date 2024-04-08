package com.study.board.service;

import com.study.board.dto.requests.BoardCreateRequest;
import com.study.board.dto.response.BoardReadResponse;
import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.user.entity.User;
import com.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void write(BoardCreateRequest dto) {
        // 현재 인증된 사용자의 Authentication 객체를 얻음
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authentication 객체로부터 사용자의 이름(또는 이메일 등의 식별 정보)을 얻음
        String nickName = authentication.getName();
        User user = userRepository.findByUserId(nickName).orElse(null);
        if (user != null){
            boardRepository.save(Board.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .view(0)
                    .user(user)
                    .createdDate(LocalDateTime.now())
                    .build());
        }
    }

    public BoardReadResponse read(Integer id){
        Board board = boardRepository.findById(id).orElse(null);
        return BoardReadResponse.builder()
                .id(board.getId())
                .nickName(board.getUser() != null ? board.getUser().getNickName() : null)
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    public List<BoardReadResponse> readAll(){
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> BoardReadResponse.builder()
                        .id(board.getId())
                        .nickName(board.getUser() != null ? board.getUser().getNickName() : null)
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .collect(Collectors.toList());
    }
}
