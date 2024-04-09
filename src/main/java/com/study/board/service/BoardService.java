package com.study.board.service;

import com.study.board.dto.requests.BoardCreateRequest;
import com.study.board.dto.response.BoardResponse;
import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.user.dto.CustomUserDetails;
import com.study.user.entity.User;
import com.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    public BoardResponse read(Integer id){
        Board board = boardRepository.findById(id).orElse(null);
        return BoardResponse.builder()
                .id(board.getId())
                .nickName(board.getUser() != null ? board.getUser().getNickName() : null)
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    public List<BoardResponse> readAll(){
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> BoardResponse.builder()
                        .id(board.getId())
                        .nickName(board.getUser() != null ? board.getUser().getNickName() : null)
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public BoardResponse editP(Integer id) {
        Board board = boardRepository.findById(id).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if(!board.getUser().getUserId().equals(customUserDetails.getUsername())) {
            System.out.println("게시글 작성자만 수정 가능합니다");
            return null;
        }else{
            BoardResponse response = BoardResponse.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            return response;
        }
    }

    public BoardResponse edit(BoardCreateRequest dto, Integer id) {
        Board board = boardRepository.findById(id).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if(!board.getUser().getUserId().equals(customUserDetails.getUsername())) {
            System.out.println("게시글 작성자만 수정 가능합니다");
            return null;
        }else{
            board.update(dto.getTitle(), dto.getContent());
            BoardResponse response = BoardResponse.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardRepository.save(board);
            return response;
        }
    }
}
