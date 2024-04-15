package com.study.anonyboard.controller;

import com.study.anonyboard.dto.requests.AnonyBoardRequest;
import com.study.anonyboard.dto.response.AnonyBoardResponse;
import com.study.anonyboard.service.AnonyBoardService;
import com.study.user.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AnonyBoardController {
    private final AnonyBoardService anonyBoardService;

    // C(Create)
    @GetMapping("/anonyboard/create")
    public String createP(){
        return "anonyboard/create";
    }

    @PostMapping("/anonyboard/create")
    public String create(AnonyBoardRequest dto){
        anonyBoardService.write(dto);
        return "redirect:/anonyboard/list";
    }

    // R(Read)
    @GetMapping("/anonyboard/{id}")
    public String detailP(Model model, @PathVariable("id") Integer id){
        AnonyBoardResponse anonyBoardResponse = anonyBoardService.findById(id);
        model.addAttribute("board", anonyBoardResponse);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("nickName", customUserDetails.getNickName());
            boolean isWriter = anonyBoardService.isWriter(customUserDetails.getId(), id);
            model.addAttribute("isWriter", isWriter);
        }
        return "anonyboard/detail";
    }

    @GetMapping("/anonyboard/list")
    public String listP(Model model, @RequestParam(value="page", defaultValue = "0") Integer page){
        Page<AnonyBoardResponse> paging = anonyBoardService.getList(page);
        model.addAttribute("currentPage", "anonyboard");
        model.addAttribute("paging", paging);
        return "anonyboard/list";
    }

    // U(Update)
    @GetMapping("/board/edit/{id}")
    public String editP(@PathVariable("id") Integer id, Model model){
        AnonyBoardResponse anonyBoardResponse = anonyBoardService.editP(id);
        if(anonyBoardResponse != null){
            model.addAttribute("board", anonyBoardResponse);
            return "board/edit";
        }else{
            return "redirect:/login";
        }
    }
}
