package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.BoardDTO;
import bisan.internship.devtrack.repository.BoardRepo;
import bisan.internship.devtrack.service.BoardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @PostMapping("{roleId}")
    public ResponseEntity<BoardDTO> createBoard(@Valid @RequestBody BoardDTO boardDTO
                                                ,@PathVariable("roleId") long roleId) {
//        long projectId = boardDTO.getProjectId();
//        Long roleId = boardDTO.getRoleId();

        BoardDTO createdBoard = boardService.createBoard(boardDTO.getProjectId(),roleId);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @GetMapping("{boardId}")
    public ResponseEntity<BoardDTO> getBoards(@PathVariable("boardId") long boardId) {
        BoardDTO boardDTO = boardService.getBoardById(boardId);
        return ResponseEntity.ok(boardDTO);
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> boardDTO = boardService.getAllBoards();
        return ResponseEntity.ok(boardDTO);
    }

    @GetMapping("projects/{projectId}")
    public ResponseEntity<List<BoardDTO>> getBoardsByProject(@PathVariable("projectId") long projectId) {
        List<BoardDTO> boardDTO = boardService.getBoardsByProjectId(projectId);
        return ResponseEntity.ok(boardDTO);
    }

    @PutMapping("{boardId}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable("boardId") long boardId,
                                                @Valid @RequestBody BoardDTO updatedDTO) {
        BoardDTO boardDto = boardService.updateBoard(boardId, updatedDTO);
        return ResponseEntity.ok(boardDto);
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardId") long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("Board Deleted Successfully");
    }

    @PostMapping("projects/{projectId}/default")
    public ResponseEntity<String> addDefaultBoards(@PathVariable("projectId") long projectId) {
        boardService.addDefaultBoards(projectId);
        return ResponseEntity.ok("Default Boards Added Successfully");
    }

}
