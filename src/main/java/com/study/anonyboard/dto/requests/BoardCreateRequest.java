package com.study.anonyboard.dto.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
