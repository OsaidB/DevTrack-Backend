package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO createBoard(BoardDTO boardDto);
    BoardDTO getBoardById(Long boardId);
    List<BoardDTO> getAllBoards();
    BoardDTO updateBoard(Long boardId, BoardDTO updatedBoard);
    void deleteBoard(Long boardId);
}
