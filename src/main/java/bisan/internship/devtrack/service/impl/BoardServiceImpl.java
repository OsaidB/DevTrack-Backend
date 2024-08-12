package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.BoardDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.BoardMapper;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Role;
import bisan.internship.devtrack.repository.BoardRepo;
import bisan.internship.devtrack.repository.ProjectRepo;
import bisan.internship.devtrack.repository.RoleRepo;
import bisan.internship.devtrack.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private RoleRepo roleRepo;
//    @Autowired
//    private BoardMapper boardMapper;

    @Override
    public BoardDTO createBoard(Long projectId, Long roleId) {

        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Board board = new Board();
        board.setName(role.getRoleName());

        board.setProject(projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId)));

        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());
        Board saveBoard = boardRepo.save(board);
        return BoardMapper.INSTANCE.toBoardDTO(saveBoard);
    }

    @Override
    public BoardDTO getBoardById(Long boardId) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        return BoardMapper.INSTANCE.toBoardDTO(board);
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepo.findAll();
        return boards.stream().map(BoardMapper.INSTANCE::toBoardDTO).collect(Collectors.toList());
    }

    @Override
    public List<BoardDTO> getBoardsByProjectId(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        List<Board> boards = boardRepo.findByProjectId(project);
        return boards.stream().map(boardMapper::toBoardDTO).collect(Collectors.toList());
    }

    @Override
    public BoardDTO updateBoard(Long boardId, BoardDTO updatedBoard) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        Project project = projectRepo.findById(updatedBoard.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + updatedBoard.getProjectId()));

        board.setProject(project);
        board.setName(updatedBoard.getName());
//        board.setCreatedAt(updatedBoard.getCreatedAt());
        board.setUpdatedAt(updatedBoard.getUpdatedAt());

        Board saveBoard = boardRepo.save(board);
        return BoardMapper.INSTANCE.toBoardDTO(saveBoard);
    }

    @Override
    public void deleteBoard(Long boardId) {
        Board board = boardRepo.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        boardRepo.delete(board);
    }

}
