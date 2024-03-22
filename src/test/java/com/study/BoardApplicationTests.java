package com.study;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BoardApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void boardtest(){
        boardRepository.save(Board.builder()
                .title("테스트1")
                .content("테스트1내용")
                .createdDate(LocalDateTime.now())
                .build());
        List<Board> boards = boardRepository.findAll();
        for(Board i: boards){
            System.out.println(i);
        }
    }

}

