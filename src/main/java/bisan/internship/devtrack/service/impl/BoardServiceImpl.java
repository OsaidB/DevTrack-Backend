package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.BoardDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.BoardMapper;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.repository.BoardRepo;
import bisan.internship.devtrack.repository.ProjectRepo;
import bisan.internship.devtrack.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Project project = projectRepo.findById(boardDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + boardDTO.getProjectId()));

        Board board = BoardMapper.mapToBoardEntity(boardDTO, project);
        Board saveBoard = boardRepo.save(board);
        return BoardMapper.mapToBoardDto(saveBoard);
    }

    @Override
    public BoardDTO getBoardById(Long boardId) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        return BoardMapper.mapToBoardDto(board);
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepo.findAll();
        return boards.stream().map(BoardMapper::mapToBoardDto).collect(Collectors.toList());
    }

    @Override
    public BoardDTO updateBoard(Long boardId, BoardDTO updatedBoard) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        Project project = projectRepo.findById(updatedBoard.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + updatedBoard.getProjectId()));

        board.setProjecId(project);
        board.setName(updatedBoard.getName());
        board.setCreatedAt(updatedBoard.getCreatedAt());
        board.setUpdatedAt(updatedBoard.getUpdatedAt());

        Board saveBoard = boardRepo.save(board);
        return BoardMapper.mapToBoardDto(saveBoard);
    }

    @Override
    public void deleteBoard(Long boardId) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        boardRepo.deleteById(boardId);
    }

}
