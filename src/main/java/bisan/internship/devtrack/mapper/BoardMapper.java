package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.BoardDTO;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);



    @Mappings({
//            @Mapping(target = "role", ignore = true),
            @Mapping(source = "project.projectId", target = "projectId")

    })
    BoardDTO toBoardDTO(Board board);

    @Mappings({
            @Mapping(target = "role", ignore = true),
            @Mapping(source = "projectId", target = "project.projectId")

    }) // The role will be set in the service
    Board toBoardEntity(BoardDTO boardDTO);

    default Long map(Project project) {
        return project == null ? null : project.getProjectId();
    }

    default Project map(Long projectId) {
        if (projectId == null) {
            return null;
        }
        Project project = new Project();
        project.setProjectId(projectId);
        return project;
    }
}
