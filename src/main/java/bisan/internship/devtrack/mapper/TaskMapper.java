package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.TaskDTO;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mappings({
            @Mapping(source = "project.projectId", target = "projectId"),
            @Mapping(source = "assignedTo.userId", target = "assignedToUserId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL),
            @Mapping(source = "board.boardId", target = "boardId")
    })
    TaskDTO toTaskDTO(Task task);

    @Mappings({
            @Mapping(source = "projectId", target = "project.projectId"),
            @Mapping(source = "assignedToUserId", target = "assignedTo.userId"),
            @Mapping(source = "boardId", target = "board.boardId")
    })
    Task toTaskEntity(TaskDTO taskDTO);

    default Long mapProjectToId(Project project) {
        return project == null ? null : project.getProjectId();
    }

    default Project mapIdToProject(Long projectId) {
        if (projectId == null) {
            return null;
        }
        Project project = new Project();
        project.setProjectId(projectId);
        return project;
    }

    default Long mapUserToId(User user) {
        return user == null ? null : user.getUserId();
    }

    default User mapIdToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    default Long mapBoardToId(Board board) {
        return board == null ? null : board.getBoardId();
    }

    default Board mapIdToBoard(Long boardId) {
        if (boardId == null) {
            return null;
        }
        Board board = new Board();
        board.setBoardId(boardId);
        return board;
    }
}
