package com.jeeproj.company.assignment.service.mapper;

import com.jeeproj.company.assignment.dto.AssignmentDTO;
import com.jeeproj.company.assignment.entity.Assignment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AssignmentMapper {
    AssignmentDTO toAssignmentDTO(Assignment assignment);

    Assignment toAssignment(AssignmentDTO assignmentDTO);

    List<AssignmentDTO> toAssignmentDTOs(List<Assignment> assignments);


}
