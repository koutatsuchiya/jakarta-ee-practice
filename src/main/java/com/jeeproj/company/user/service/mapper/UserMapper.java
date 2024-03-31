package com.jeeproj.company.user.service.mapper;

import com.jeeproj.company.user.dto.SignUpDTO;
import com.jeeproj.company.user.dto.UserDTO;
import com.jeeproj.company.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface UserMapper {
    UserDTO toUserDTO(User user);

    @Mapping(target = "password", ignore = true)
    User toUser(SignUpDTO signUpDTO);

    List<UserDTO> toUserDTOs(List<User> users);
}