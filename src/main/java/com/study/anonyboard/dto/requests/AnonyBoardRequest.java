package com.study.anonyboard.dto.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnonyBoardRequest {
    private String title;
    private String content;

    @Builder
    public AnonyBoardRequest(String title, String content){
        this.title = title;
        this.content = content;
    }
}
