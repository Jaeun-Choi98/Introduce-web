package com.example.introduce.controller;

import com.example.introduce.model.Board;
import com.example.introduce.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class StudyController {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/study-home")
    public String studyHome(Model model, @PageableDefault(size = 5,sort="id",direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "") String searchText){
        //Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTopicContaining(searchText,pageable);
        int startPage = Math.max(1,boards.getPageable().getPageNumber()-2);
        int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber()+2);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);
        return "study/study-home";
    }

    @GetMapping("/write-home")
    public String goWrite(Model model, @RequestParam(required = false) Long id){
        if(id==null){
            model.addAttribute("board",new Board());
        }else{
            //글 수정
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }
        return "study/write-home";
    }

    @PostMapping("/write-home")
    public String submitWrite(@ModelAttribute Board board){
        boardRepository.save(board);
        return "redirect:/study-home";
    }

    @GetMapping("/look-home")
    public String look(Model model, @RequestParam(required = false) Long id){
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board",board);
        return "study/look-home";
    }

}
