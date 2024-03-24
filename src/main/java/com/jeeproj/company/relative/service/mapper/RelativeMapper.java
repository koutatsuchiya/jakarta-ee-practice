package com.jeeproj.company.relative.service.mapper;

import com.jeeproj.company.relative.dto.RelativeDTO;
import com.jeeproj.company.relative.dto.RelativeRequestDTO;
import com.jeeproj.company.relative.entity.Relative;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface RelativeMapper {
    RelativeDTO toRelativeDTO(Relative relative);

    Relative toRelative(RelativeRequestDTO relativeRequestDTO);

    List<RelativeDTO> toRelativeDTOs(List<Relative> relatives);

    void updateRelative(@MappingTarget Relative relative, RelativeRequestDTO relativeRequestDTO);
}
