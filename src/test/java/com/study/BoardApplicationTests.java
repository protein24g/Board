package com.study;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BoardApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void boardtest(){
        for(int i = 0; i <50; i++){
            boardRepository.save(Board.builder()
                    .title(String.format("글작성[%d]",i))
                    .content("테스트1내용")
                    .user(null)
                    .createdDate(LocalDateTime.now())
                    .build());
            List<Board> boards = boardRepository.findAll();
        }
    }

}

