package com.study.board.service;

import com.study.board.dto.requests.BoardCreateRequest;
import com.study.board.dto.response.BoardResponse;
import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.user.dto.CustomUserDetails;
import com.study.user.entity.User;
import com.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Page<BoardResponse> getList(Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Board> boards = boardRepository.findAll(pageable);

        Page<BoardResponse> boardResponses = boards
                .map(board -> BoardResponse.builder()
                            .id(board.getId())
                            .nickName(board.getUser() != null ? board.getUser().getNickName() : null)
                            .title(board.getTitle())
                            .content(board.getContent())
                            .createdDate(board.getCreatedDate())
                            .build());
        return boardResponses;
    }

    public void write(BoardCreateRequest dto) {
        // 현재 인증된 사용자의 Authentication 객체를 얻음
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userRepository.findByUserId(customUserDetails.getUsername()).orElse(null);
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
    }

    public BoardResponse findById(Integer id){
        Board board = boardRepository.findById(id).orElse(null);
        return BoardResponse.builder()
                .id(board.getId())
                .nickName(board.getUser() != null ? board.getUser().getNickName() : null)
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
    }

    public List<BoardResponse> findAll(){
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> BoardResponse.builder()
                        .id(board.getId())
                        .nickName(board.getUser() != null ? board.getUser().getNickName() : null)
                        .title(board.getTitle())
                        .content(board.getContent())
                        .createdDate(board.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BoardResponse> findAllByUserId(Integer Id){
        List<Board> boards = boardRepository.findAllByUserId(Id).orElse(null);
        List<BoardResponse> boardResponses = boards.stream().map(board -> {
            return BoardResponse.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate()) // 날짜 형식 변환
                    .build(); // 빌더를 사용하여 BoardResponse 객체 생성
        }).collect(Collectors.toList());
        return boardResponses;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            Board board = boardRepository.findById(id).orElse(null);
            if (!board.getUser().getUserId().equals(customUserDetails.getUsername())) {
                System.out.println("게시글 작성자만 수정 가능합니다");
                return null;
            } else {
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
        System.out.println("로그인 후 이용 하세요.");
        return null;
    }

    public boolean delete(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Board board = boardRepository.findById(id).orElse(null);
            if(board != null){
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
                if(!board.getUser().getUserId().equals(customUserDetails.getUsername()))
                    return false;
                else{
                    boardRepository.delete(board);
                    return true;
                }
            }
        }
        return false;
    }
}
