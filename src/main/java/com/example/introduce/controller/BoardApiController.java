package com.example.introduce.controller;


import com.example.introduce.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    BoardRepository boardRepository;

    @DeleteMapping("/api/delete/{id}")
    public void delete(@PathVariable Long id){
        boardRepository.deleteById(id);
    }
}
