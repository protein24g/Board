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
    private String nickName;
    private String title;
    private String content;

    @Builder
    public BoardReadResponse(Integer id, String nickName, String title, String content){
        this.id = id;
        this.nickName = nickName;
        this.title = title;
        this.content = content;

    }
}
