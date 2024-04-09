package com.study.board.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardResponse {
    private Integer id;
    private LocalDateTime createdDate;
    private String nickName;
    private String title;
    private String content;

    @Builder
    public BoardResponse(Integer id, LocalDateTime createdDate, String nickName, String title, String content){
        this.id = id;
        this.createdDate = createdDate;
        this.nickName = nickName;
        this.title = title;
        this.content = content;
    }
}
