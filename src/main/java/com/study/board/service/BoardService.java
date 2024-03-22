package com.study.board.service;

import com.study.board.dto.requests.BoardCreateRequest;
import com.study.board.dto.response.BoardCreateResponse;
import com.study.board.dto.response.BoardReadResponse;
import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardCreateResponse write(BoardCreateRequest dto) {
        Board saved = boardRepository.save(BoardCreateRequest.toEntity(dto, null));
        return BoardCreateResponse.toDto(saved);
    }

    public List<BoardReadResponse> readAll(){
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardReadResponse::toDto)
                .collect(Collectors.toList());
    }
}
