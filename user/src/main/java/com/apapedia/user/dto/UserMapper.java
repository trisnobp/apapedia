package com.apapedia.user.dto;

import com.apapedia.user.dto.response.UserDataResponse;
import com.apapedia.user.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDataResponse userToUserDataResponse(User user);

}
