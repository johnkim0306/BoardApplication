package board.boardspring.controller;

import board.boardspring.dto.BoardDTO;
import board.boardspring.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }


    @GetMapping("/")
    public String findAll(Model model) {
        // Find the boarder from the database
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {

        // update views
        boardService.updateHits(id);
        // get the boarder in dto
        BoardDTO boardDTO = boardService.findById(id);
        // and put that in a model
        model.addAttribute("board", boardDTO);
        return "detail";
    }
}
