package com.study.board.dto.response;

import com.study.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardCreateResponse {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public BoardCreateResponse(Integer id, String title, String content, LocalDateTime createdDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public static BoardCreateResponse toDto(Board board){
        return BoardCreateResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
    }
}
