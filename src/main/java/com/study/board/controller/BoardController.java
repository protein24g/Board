package com.study.board.controller;

import com.study.board.dto.requests.BoardCreateRequest;
import com.study.board.dto.response.BoardResponse;
import com.study.board.service.BoardService;
import com.study.user.dto.CustomUserDetails;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String indexP(Model model){
        return "redirect:/board/list";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            Page<BoardResponse> boardResponses = boardService.findAllByUserId(customUserDetails.getId(), page);
            model.addAttribute("paging", boardResponses);
            return "user/mypage";
        }
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
        BoardResponse boardResponse = boardService.findById(id);
        model.addAttribute("board", boardResponse);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("nickName", customUserDetails.getNickName());
        }
        return "board/detail";
    }

    @GetMapping("/board/list")
    public String listP(Model model, @RequestParam(value="page", defaultValue = "0") Integer page){
        Page<BoardResponse> paging = boardService.getList(page);
        model.addAttribute("paging", paging);
        return "board/list";
    }

    // U(Update)
    @GetMapping("/board/edit/{id}")
    public String editP(@PathVariable("id") Integer id, Model model){
        BoardResponse board = boardService.editP(id);
        if(board != null){
            model.addAttribute("board", board);
            return "board/edit";
        }else{
            return "redirect:/login";
        }
    }

    @PostMapping("/board/edit/{id}")
    public String edit(BoardCreateRequest dto, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        BoardResponse board = boardService.edit(dto, id);
        if(board != null)
            redirectAttributes.addFlashAttribute("message", "수정 완료");
        else
            redirectAttributes.addFlashAttribute("message", "수정 실패");
        return "redirect:/board/" + id;
    }

    // D(Delete)
    @PostMapping("/board/{id}")
    public String delete(@PathVariable("id") Integer id){
        boolean response = boardService.delete(id);
        if(response)
            System.out.println("삭제 완료");
        else
            System.out.println("삭제 실패");
        return "redirect:/board/list";
    }
}
