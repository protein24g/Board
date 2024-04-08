package com.study.board.controller;

import com.study.board.dto.requests.BoardCreateRequest;
import com.study.board.dto.response.BoardReadResponse;
import com.study.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String indexP(Model model){

        return "redirect:/board/list";
    }

    // C(Create)
    @GetMapping("/board/create")
    public String createP(){
        return "board/create";
    }

    @PostMapping("/board/create")
    public String create(BoardCreateRequest dto){
        boardService.write(dto);
        return "redirect:/board/list";
    }

    // R(Read)
    @GetMapping("/board/{id}")
    public String detailP(Model model, @PathVariable("id") Integer id){
        BoardReadResponse boardReadResponse = boardService.read(id);
        model.addAttribute("board", boardReadResponse);
        return "board/detail";
    }

    @GetMapping("/board/list")
    public String listP(Model model){
        List<BoardReadResponse> boardList = boardService.readAll();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    // U(Update)

    // D(Delete)



}
