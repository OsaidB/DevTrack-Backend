package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.BoardDTO;
import bisan.internship.devtrack.dto.RoleDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.BoardMapper;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.FunctionalRole;
import bisan.internship.devtrack.repository.BoardRepo;
import bisan.internship.devtrack.repository.ProjectRepo;
import bisan.internship.devtrack.repository.FuncRoleRepo;
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
    private FuncRoleRepo funcRoleRepo;

    @Autowired
    private final FuncRoleServiceImpl FuncRoleService; // Inject RoleServiceImpl

    private final RoleConfigurationService roleConfigurationService;

//    @Autowired
//    private BoardMapper boardMapper;

    @Override
    public BoardDTO createBoard(Long projectId, Long roleId) {

        FunctionalRole role = funcRoleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
//        if (role == null) {
//            throw new RuntimeException("Role not found");
//        }

        Board board = new Board();
        board.setName(role.getRoleName());
        board.setRole(role);

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        board.setProject(project);
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        Board savedBoard = boardRepo.save(board);
        return BoardMapper.INSTANCE.toBoardDTO(savedBoard);
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
        projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        List<Board> boards = boardRepo.findByProjectProjectId(projectId);
        return boards.stream().map(BoardMapper.INSTANCE::toBoardDTO).collect(Collectors.toList());
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

    @Override
    public void addDefaultBoards(long projectId) {
        // Create default boards
        // Example IDs for default roles
//        Long backendRoleId = RoleConstants.BACKEND_ROLE_ID;
//        Long frontendRoleId = RoleConstants.FRONTEND_ROLE_ID;
//        Long qaRoleId = RoleConstants.QA_ROLE_ID;

        // Get or create roles
        RoleDTO backendRole = FuncRoleService.getOrCreateRole("Backend");
        RoleDTO frontendRole = FuncRoleService.getOrCreateRole("Frontend");
        RoleDTO qaRole = FuncRoleService.getOrCreateRole("QA");

        // Save role IDs to configuration
        roleConfigurationService.setBackendRoleId(backendRole.getRoleId());
        roleConfigurationService.setFrontendRoleId(frontendRole.getRoleId());
        roleConfigurationService.setQaRoleId(qaRole.getRoleId());

//        if (!roleService.isRoleName(backendRoleId, "Backend")) {
//            throw new RuntimeException("Invalid role ID for Backend");
//        }
//        if (!roleService.isRoleName(frontendRoleId, "Frontend")) {
//            throw new RuntimeException("Invalid role ID for Frontend");
//        }
//        if (!roleService.isRoleName(qaRoleId, "QA")) {
//            throw new RuntimeException("Invalid role ID for QA");
//        }


        createBoard(projectId, backendRole.getRoleId());
        createBoard(projectId, frontendRole.getRoleId());
        createBoard(projectId, qaRole.getRoleId());
    }

}
