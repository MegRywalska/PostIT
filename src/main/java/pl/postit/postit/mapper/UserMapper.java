package pl.postit.postit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import pl.postit.postit.dto.UserDTO;
import pl.postit.postit.dto.UserRequestDTO;
import pl.postit.postit.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "username", target = "username"),
    })
    User mapUserRequestDTOToUser(UserRequestDTO dto);

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "username", target = "username"),
    })
    User mapUserDTOToUser(UserDTO dto);

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "username", target = "username"),
    })
    UserDTO mapUserToUserDTO(User dto);
}
