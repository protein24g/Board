package com.study.board.dto.requests;

import lombok.*;

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
}
