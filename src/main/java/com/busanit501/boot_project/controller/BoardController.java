package com.busanit501.boot_project.controller;

import com.busanit501.boot_project.dto.BoardDTO;
import com.busanit501.boot_project.dto.PageRequestDTO;
import com.busanit501.boot_project.dto.PageResponseDTO;
import com.busanit501.boot_project.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        // 서비스 외주 이용해서, 데이터 가져오기
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info("BoardController에서, list, responseDTO : {}", responseDTO);
        // 서버 -> 화면으로 데이터 전달.
        model.addAttribute("responseDTO", responseDTO);
    }

//    등록화면 작업, get
    @GetMapping("/register")
    public void register() {
    }
    // 등록 처리, post
    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("BoardController 에서, registerPost 작업중");
        if (bindingResult.hasErrors()) {
            log.info("registerPost, 입력 작업중, 유효성 체크에 해당 사항 있음");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        log.info("넘어온 데이터 확인 boardDTO : " + boardDTO);
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno);
        return  "redirect:/board/list";
    }

    @GetMapping("/read")// 화면 경로 : /board/read.html 작업함.
    // 예시
    //http://localhost:8080/board/list?type=tcw&keyword=1&page=2
    // type, keyword, page, -> PageRequestDTO의 멤버 이름과 동일함.
    // 그래서, 자동 수집함. !!중요!!
    // 자동 화면으로 전달도 함. !!중요!!
    public void read(Long bno, PageRequestDTO pageRequestDTO,
                     Model model) {
        // 누구에게 외주 줄까요? BoardService  외주,
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info("BoardController 에서, read 작업중 boardDTO: "+ boardDTO);
        // 서버 -> 화면, 데이터 전달,
        model.addAttribute("dto", boardDTO);

    }

}
