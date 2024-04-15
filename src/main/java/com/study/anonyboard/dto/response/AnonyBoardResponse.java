package com.study.anonyboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AnonyBoardResponse {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public AnonyBoardResponse(Integer id, String title, String content, LocalDateTime createdDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
