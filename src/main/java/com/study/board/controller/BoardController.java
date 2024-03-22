package com.study.board.controller;

import com.study.board.dto.requests.BoardCreateRequest;
import com.study.board.dto.response.BoardCreateResponse;
import com.study.board.dto.response.BoardReadResponse;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/create")
    public String create_page(){
        return "board/create";
    }

    @GetMapping("")
    public String list(Model model){
        List<BoardReadResponse> boardList = boardService.readAll();
        model.addAttribute("boardList", boardListd);
        return "board/list";
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<BoardCreateResponse> create(@RequestBody BoardCreateRequest dto){
        BoardCreateResponse boardCreatResponse = boardService.write(dto);
        return (boardCreatResponse != null) ?
                ResponseEntity.status(HttpStatus.OK).body(boardCreatResponse) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
