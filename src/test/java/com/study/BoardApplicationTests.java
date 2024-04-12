package com.study;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import com.study.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BoardApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void boardtest(){
        Board board = boardRepository.findById(2).orElse(null);

        for(int i = 0; i < 300; i++){
            boardRepository.save(Board.builder()
                    .title(String.format("글작성[%d]",i))
                    .content("테스트1내용")
                    .user(board.getUser() != null ? board.getUser() : null)
                    .createdDate(LocalDateTime.now())
                    .build());
        }
    }

}

