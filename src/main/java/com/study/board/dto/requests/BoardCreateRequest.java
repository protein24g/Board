package com.study.board.dto.requests;

import com.study.board.entity.Board;
import com.study.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardCreateRequest {
    private String title;
    private String content;

    @Builder
    public BoardCreateRequest(String title, String content){
        this.title = title;
        this.content = content;
    }

    public static Board toEntity(BoardCreateRequest dto, Member member){
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdDate(LocalDateTime.now())
                .view(0)
                .member(member)
                .build();
    }
}
