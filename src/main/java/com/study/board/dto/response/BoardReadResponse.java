package com.study.board.dto.response;

import com.study.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardReadResponse {
    private Integer id;
    private String title;
    private String content;

    @Builder
    public BoardReadResponse(Integer id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static BoardReadResponse toDto(Board board) {
        return BoardReadResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
