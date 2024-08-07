package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.BoardDTO;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;

public class BoardMapper {
    public static BoardDTO mapToBoardDto(Board board) {
        return new BoardDTO(
                board.getBoardId(),
                board.getName(),
                board.getProjectId().getProjectId(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
    public static Board mapToBoardEntity(BoardDTO boardDTO, Project project) {
        return new Board(
                boardDTO.getBoardId(),
                boardDTO.getName(),
                project,
                boardDTO.getUpdatedAt(),
                boardDTO.getCreatedAt()
        );
    }
}
