package com.jeeproj.company.relative.service.mapper;

import com.jeeproj.company.relative.dto.RelativeDTO;
import com.jeeproj.company.relative.entity.Relative;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface RelativeMapper {
    RelativeDTO toRelativeDTO(Relative relative);

    Relative toRelative(RelativeDTO relativeDTO);

    List<RelativeDTO> toRelativeDTOs(List<Relative> relatives);
}
