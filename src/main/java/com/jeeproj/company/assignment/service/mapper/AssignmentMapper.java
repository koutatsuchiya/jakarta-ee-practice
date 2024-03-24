package com.jeeproj.company.assignment.service.mapper;

import com.jeeproj.company.assignment.dto.AssignmentDTO;
import com.jeeproj.company.assignment.dto.AssignmentRequestDTO;
import com.jeeproj.company.assignment.entity.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface AssignmentMapper {
    AssignmentDTO toAssignmentDTO(Assignment assignment);

    Assignment toAssignment(AssignmentRequestDTO assignmentRequestDTO);

    List<AssignmentDTO> toAssignmentDTOs(List<Assignment> assignments);

    void updateAssignment(@MappingTarget Assignment assignment, AssignmentRequestDTO assignmentRequestDTO);
}
